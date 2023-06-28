package com.singularity_code.codebase.util

import android.app.Activity
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

val Activity.ViewScope get() = MainScope()

fun <T> Activity.collect(
    provider: Flow<T>,
    block: suspend (T) -> Unit
) = ViewScope.launch {
    provider.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
fun <T> Activity.collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
): Job = ViewScope.launch {
    state.onEach {
        block.invoke(it)
    }.collect()
}