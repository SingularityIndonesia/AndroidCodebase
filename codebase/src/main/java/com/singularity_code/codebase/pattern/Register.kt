package com.singularity_code.codebase.pattern

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

interface Register<T> {

    fun set(data: T)

    val data: Flow<T>

    suspend fun first() =
        data.first()

    @Composable
    fun collectAsState(default: T) =
        data.collectAsState(default)
}