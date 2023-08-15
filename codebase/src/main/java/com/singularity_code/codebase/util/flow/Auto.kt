package com.singularity_code.codebase.util.flow

import androidx.lifecycle.ViewModel

fun <R> ViewModel.auto(block: () -> R): Lazy<R> = lazy { block.invoke() }
