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
val Activity.ViewScope get() = MainScope()
fun Activity.viewJob(block: suspend () -> Unit): Job =
    ViewScope.launch {
        block.invoke()
    }

/** # Fragment **/
val Fragment.ViewScope get() = viewLifecycleOwner.lifecycleScope
fun Fragment.viewJob(
    block: suspend () -> Unit
) = ViewScope.launch {
    block.invoke()
}

/** # ViewModel **/
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