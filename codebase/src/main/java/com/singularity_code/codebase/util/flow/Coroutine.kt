package com.singularity_code.codebase.util.flow

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/** # Activity **/
context (Activity)
val ViewScope
    get() = MainScope()

context (Activity)
fun viewJob(block: suspend () -> Unit): Job =
    ViewScope.launch {
        block.invoke()
    }

/** # Fragment **/
context (Fragment)
val ViewScope
    get() = viewLifecycleOwner.lifecycleScope

context (Fragment)
fun viewJob(
    block: suspend () -> Unit
) = ViewScope.launch {
    block.invoke()
}

/** # ViewModel **/
context (ViewModel)
fun viewModelJob(
    superVisorJob: CompletableJob = SupervisorJob(),
    block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(
        viewModelScope.coroutineContext + superVisorJob
    ) {
        block.invoke(this)
    }
}