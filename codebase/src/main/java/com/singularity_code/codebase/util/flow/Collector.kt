package com.singularity_code.codebase.util.flow

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.singularity_code.codebase.pattern.LazyFunction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach

/** # Activity **/
context (Activity)
fun <T> collect(
    provider: Flow<T>,
    block: suspend (T) -> Unit
) = viewJob {
    provider.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
context (Activity)
fun <T> collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
) = viewJob {
    state.onEach {
        block.invoke(it)
    }.collect()
}

/** # Fragment **/
context (Fragment)
fun <T> collect(
    provider: Flow<T>,
    block: suspend (T) -> Unit
) = viewJob {
    provider.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
context (Fragment)
fun <T> collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
) = viewJob {
    state.onEach {
        block.invoke(it)
    }.collect()
}

/** # ViewModel **/

context (ViewModel)
fun <T> collect(
    state: Flow<T>,
    block: suspend (T) -> Unit
) = viewModelJob {
    state.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
context (ViewModel)
fun <T> collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
) = viewModelJob {
    state.onEach {
        block.invoke(it)
    }.collect()
}

context (ViewModel)
fun updateOn(
    vararg flows: Pair<Flow<*>, Boolean>,
    block: suspend () -> Unit
): LazyFunction {
    val updater = lazyFunction { block.invoke() }

    flows.forEach { flow ->
        collect(
            flow.first,
        ) { _ ->
            if (flow.second)
                updater.forceInvoke(
                    "$flow ${System.currentTimeMillis()}"
                )
            else
                updater.tryInvoke(
                    "$flow ${System.currentTimeMillis()}"
                )
        }
    }

    return updater
}