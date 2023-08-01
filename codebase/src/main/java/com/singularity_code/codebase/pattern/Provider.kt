package com.singularity_code.codebase.pattern

import arrow.core.Either
import com.singularity_code.codebase.util.serialization.ErrorMessage
import kotlinx.coroutines.flow.Flow

interface Provider<P : Payload, D : Any> {

    val state: Flow<VMData<D>>

    val success: Flow<Pair<Boolean, D?>>

    val failed: Flow<Pair<Boolean, String?>>

    val loading: Flow<Boolean>

    val operator: suspend (P) -> Flow<Either<ErrorMessage, D>>

    fun update(
        payload: P
    )
}