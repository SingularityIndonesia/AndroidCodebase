package com.singularity_code.codebase.util.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <R> ViewModel.auto(block: () -> R): Lazy<R> = lazy { block.invoke() }

context (ViewModel)
fun <T> T.automate(block: suspend (T) -> Unit): T = this.also {
    viewModelScope.launch {
        block.invoke(it)
    }
}
