package com.singularity_code.codebase.util.flow

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.singularity_code.codebase.pattern.Payload
import com.singularity_code.codebase.pattern.Provider
import com.singularity_code.codebase.pattern.VMData
import com.singularity_code.codebase.pattern.v2.CDBS_V1
import com.singularity_code.codebase.util.serialization.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

context (CDBS_V1)
fun <P : Payload, D : Any> ViewModel.provider(
    operator: suspend (P) -> Flow<Either<ErrorMessage, D>>,
    privateContext: CoroutineContext = Dispatchers.IO + SupervisorJob(),
    retrial: Int = 3
): Lazy<Provider<P, D>> {
    return lazy {
        object : Provider<P, D> {

            private var _job: Job? = null
            private val _state: MutableStateFlow<VMData<D>> =
                MutableStateFlow(default())
            override val state: Flow<VMData<D>> = _state

            override val success: Flow<Pair<Boolean, D?>> = state.map {
                (it is VMData.Success) to
                        if (it is VMData.Success)
                            it.data
                        else null
            }

            override val failed: Flow<Pair<Boolean, String?>> = state.map {
                (it is VMData.Failed) to
                        if (it is VMData.Failed)
                            it.e.errorMessage
                        else null
            }

            override val loading: Flow<Boolean> = state.map {
                it is VMData.Loading
            }

            override val operator: suspend (P) -> Flow<Either<ErrorMessage, D>> = operator

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
                                failed(Exception(it))
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