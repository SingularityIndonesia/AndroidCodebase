package com.singularity_code.codebase.util.flow

import androidx.lifecycle.ViewModel

context (ViewModel)
fun <R> auto(block: () -> R): Lazy<R> = lazy { block.invoke() }
