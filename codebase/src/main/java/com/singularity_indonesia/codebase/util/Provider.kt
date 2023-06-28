package com.singularity_indonesia.codebase.util

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.singularity_indonesia.codebase.pattern.Payload
import com.singularity_indonesia.codebase.pattern.Provider
import com.singularity_indonesia.codebase.pattern.VMData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun <P : Payload, D : Any> ViewModel.provider(
    operator: suspend (P) -> Flow<Either<Error, D>>,
    privateContext: CoroutineContext = Dispatchers.IO + SupervisorJob(),
    retrial: Int = 3
): Lazy<Provider<P, D>> {
    return lazy {
        object : Provider<P, D> {

            private var _job: Job? = null
            private val _state: MutableStateFlow<VMData<D>> =
                MutableStateFlow(default())

            override val success: Flow<Pair<Boolean, D?>> = _state.map {
                (it is VMData.Success) to
                        if (it is VMData.Success)
                            it.data
                        else null
            }

            override val failed: Flow<Pair<Boolean, String?>> = _state.map {
                (it is VMData.Failed) to
                        if (it is VMData.Failed)
                            it.message
                        else null
            }

            override val loading: Flow<Boolean> = _state.map {
                it is VMData.Loading
            }

            override val operator: suspend (P) -> Flow<Either<Error, D>> = operator

            private lateinit var latestPayload: P
            override fun update(
                payload: P
            ) {
                latestPayload = payload

                _job?.cancel()
                _job = this@provider.viewModelScope.launch(privateContext) {

                    _state.emit(
                        loading()
                    )

                    operator.invoke(payload).onEach { result ->
                        result.map {
                            _state.emit(
                                success(it)
                            )
                        }.mapLeft {
                            _state.emit(
                                failed(
                                    it.localizedMessage ?: it.message ?: it.cause?.message
                                    ?: "unknown error"
                                )
                            )
                        }
                    }.collect()

                }
            }

            init {
                if (retrial > 0)
                    collectEach(
                        failed
                    ) {
                        if (it.first) {
                            onFailed()
                        }
                    }
            }

            private var retrialCount = 0
            private fun onFailed() {
                /** give up trying **/
                if (retrialCount >= retrial - 1) {
                    retrialCount = 0
                    return
                }

                retrialCount += 1
                Log.d("Provider", "onFailed: Retrying $retrialCount")
                update(latestPayload)
            }
        }
    }
}