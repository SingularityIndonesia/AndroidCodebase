package com.singularity_indonesia.codebase.pattern

import arrow.core.Either
import kotlinx.coroutines.flow.Flow

interface Provider<P : Payload, D : Any> {

    val success: Flow<Pair<Boolean, D?>>

    val failed: Flow<Pair<Boolean, String?>>

    val loading: Flow<Boolean>

    val operator: (suspend (P) -> Flow<Either<Error, D>>)

    fun update(
        payload: P
    )
}