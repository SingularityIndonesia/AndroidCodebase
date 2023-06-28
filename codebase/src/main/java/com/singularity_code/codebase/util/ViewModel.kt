package com.singularity_code.codebase.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun ViewModel.viewModelJob(
    superVisorJob: CompletableJob = SupervisorJob(),
    block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(
        viewModelScope.coroutineContext + superVisorJob
    ) {
        block.invoke(this)
    }
}

fun <T> ViewModel.collect(
    state: Flow<T>,
    block: suspend (T) -> Unit
): Job = viewModelJob {
    state.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
fun <T> ViewModel.collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
): Job = viewModelJob {
    state.onEach {
        block.invoke(it)
    }.collect()
}