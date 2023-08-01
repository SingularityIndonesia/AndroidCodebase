package com.singularity_code.codebase.util.flow

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

/** # Activity **/
fun <T> Activity.collect(
    provider: Flow<T>,
    block: suspend (T) -> Unit
) = viewJob {
    provider.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
fun <T> Activity.collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
) = viewJob {
    state.onEach {
        block.invoke(it)
    }.collect()
}

/** # Fragment **/
fun <T> Fragment.collect(
    provider: Flow<T>,
    block: suspend (T) -> Unit
) = viewJob {
    provider.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
fun <T> Fragment.collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
) = viewJob {
    state.onEach {
        block.invoke(it)
    }.collect()
}

/** # ViewModel **/

fun <T> ViewModel.collect(
    state: Flow<T>,
    block: suspend (T) -> Unit
) = viewModelJob {
    state.collect {
        block.invoke(it)
    }
}

/** this will collect every state even though the state is the same as it previous state **/
fun <T> ViewModel.collectEach(
    state: Flow<T>,
    block: suspend (T) -> Unit
) = viewModelJob {
    state.onEach {
        block.invoke(it)
    }.collect()
}