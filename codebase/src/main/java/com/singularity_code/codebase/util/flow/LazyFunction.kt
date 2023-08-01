package com.singularity_code.codebase.util.flow

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.singularity_code.codebase.pattern.LazyFunction
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

/** Lazy function is function that lazily executed.
 * The purpose of this function is to prevent overlapping.
 * When there are multiple instances invoking this function,
 * this function will wait till the current operation to succeed, to execute the next request.
 * if there are multiple invocation request, this function will only invoke once after the current operation succeed.
 */
fun ViewModel.lazyFunction(
    block: suspend () -> Unit
): LazyFunction {
    return object : LazyFunction {

        private val invoker = MutableStateFlow("")
        private val forceInvoker = MutableStateFlow("")
        private var invokerJob: Job? = null

        init {
            collect(
                forceInvoker
            ) {
                invokerJob?.cancel()

                invokerJob = collect(
                    invoker
                ) {
                    block.invoke()
                }
            }
        }

        override suspend fun tryInvoke(
            signature: String
        ) {
            invoker.emit(signature)
        }

        override suspend fun forceInvoke(
            signature: String
        ) {
            forceInvoker.emit(signature)
        }
    }
}

/** Lazy function is function that lazily executed.
 * The purpose of this function is to prevent overlapping.
 * When there are multiple instances invoking this function,
 * this function will wait till the current operation to succeed, to execute the next request.
 * if there are multiple invocation request, this function will only invoke once after the current operation succeed.
 */
fun Fragment.lazyFunction(
    block: suspend () -> Unit
): LazyFunction {
    return object : LazyFunction {

        private val invoker = MutableStateFlow("")
        private val forceInvoker = MutableStateFlow("")
        private var invokerJob: Job? = null

        init {
            collect(
                forceInvoker
            ) {
                invokerJob?.cancel()

                invokerJob = collect(
                    invoker
                ) {
                    block.invoke()
                }
            }
        }

        override suspend fun tryInvoke(
            signature: String
        ) {
            invoker.emit(signature)
        }

        override suspend fun forceInvoke(
            signature: String
        ) {
            forceInvoker.emit(signature)
        }

    }
}