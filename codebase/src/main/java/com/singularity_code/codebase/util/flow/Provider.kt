package com.singularity_code.codebase.util.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Option
import arrow.core.none
import arrow.core.some
import com.singularity_code.codebase.pattern.Payload
import com.singularity_code.codebase.pattern.VMData
import com.singularity_code.codebase.pattern.v2.JobSupervisor
import com.singularity_code.codebase.pattern.v2.Provider
import com.singularity_code.codebase.util.serialization.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by: stefanus
 * 27/08/23 08.51
 * Design by: stefanus.ayudha@gmail.com
 */

context (JobSupervisor, ViewModel)
fun <P : Payload, D> provider(
    operator: suspend (P) -> Result<D>,
    retrial: Int = 3
): Lazy<Provider<P, D>> {
    return lazy {
        object : Provider<P, D> {

            private var _job: Job? = null
            private val snapshot: MutableStateFlow<VMData<D>> =
                MutableStateFlow(default())

            override val loading: Flow<Boolean> = snapshot
                .map {
                    it is VMData.Loading
                }.flowOn(Dispatchers.IO)

            override val success: Flow<Option<D>> = snapshot
                .map {
                    if (it is VMData.Success)
                        it.data.some()
                    else none()
                }.flowOn(Dispatchers.IO)

            override val error: Flow<Option<ErrorMessage>> = snapshot
                .map {
                    if (it is VMData.Failed)
                        it.message.some()
                    else none()
                }.flowOn(Dispatchers.IO)

            override val operator: suspend (P) -> Result<D> = operator

            private lateinit var latestPayload: P
            override fun update(
                payload: P
            ) {
                latestPayload = payload

                _job?.cancel()
                _job = viewModelScope
                    .launch(superVisorContext) {
                        snapshot.emit(
                            loading()
                        )

                        operator.invoke(payload)
                            .onSuccess {
                                snapshot.emit(success(it))
                            }
                            .onFailure {
                                snapshot.emit(
                                    failed(it.errorMessage)
                                )
                            }

                    }
            }

            init {
                if (retrial > 0)
                    collectEach(
                        error
                    ) {
                        it.onSome {
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
                Timber.tag("Provider").d("onFailed: Retrying %d" + retrialCount)
                update(latestPayload)
            }
        }
    }
}