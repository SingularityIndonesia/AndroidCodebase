package com.singularity_code.codebase.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

val Fragment.ViewScope get() = viewLifecycleOwner.lifecycleScope

fun <T> Fragment.collect(
    provider: Flow<T>,
    block: suspend (T) -> Unit
) = ViewScope.launch {
    provider.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
fun <T> Fragment.collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
): Job = ViewScope.launch {
    state.onEach {
        block.invoke(it)
    }.collect()
}