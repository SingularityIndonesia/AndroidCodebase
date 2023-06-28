package com.singularity_code.codebase.util

import com.singularity_code.codebase.pattern.VMData

private data class DefaultImpl<T>(
    private val value: T? = null
) : VMData.Default<T>

private data class LoadingImpl<T>(
    private val value: T? = null
) : VMData.Loading<T>

private data class SuccessImpl<T>(
    override val data: T
) : VMData.Success<T>

private data class FailedImpl<T>(
    override val message: String
) : VMData.Failed<T>

fun <T> default(): VMData.Default<T> = DefaultImpl()
fun <T> loading(): VMData.Loading<T> = LoadingImpl()
fun <T> success(data: T): VMData.Success<T> = SuccessImpl(data = data)
fun <T> failed(message: String): VMData.Failed<T> = FailedImpl(message = message)