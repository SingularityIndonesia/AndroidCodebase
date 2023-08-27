package com.singularity_code.codebase.pattern

import arrow.core.Either
import arrow.core.Option
import com.singularity_code.codebase.pattern.v2.CDBS_V1
import com.singularity_code.codebase.util.serialization.ErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

context (CDBS_V1)
@Deprecated("We recommend using new version provider")
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