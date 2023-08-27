package com.singularity_code.codebase.util.io.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Created by: stefanus
 * 27/08/23 17.52
 * Design by: stefanus.ayudha@gmail.com
 */

@Composable
fun OnLifecycleEvent(
    onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit
) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun OnStartEffect(
    block: () -> Unit
) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            block.invoke()
        }
    }
}

@Composable
fun OnResumeEffect(
    block: () -> Unit
) {
    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            block.invoke()
        }
    }
}