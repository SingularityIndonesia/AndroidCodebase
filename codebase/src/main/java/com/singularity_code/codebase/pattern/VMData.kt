package com.singularity_code.codebase.pattern

sealed interface VMData<T> {
    interface Default<T> : VMData<T>
    interface Loading<T> : VMData<T>
    interface Success<T> : VMData<T> {
        val data: T
    }
    interface Failed<T> : VMData<T> {
        val e: Exception
    }
}