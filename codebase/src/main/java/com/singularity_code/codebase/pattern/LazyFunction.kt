package com.singularity_code.codebase.pattern

interface LazyFunction {
    suspend fun tryInvoke(
        signature: String
    )

    suspend fun forceInvoke(
        signature: String
    )
}