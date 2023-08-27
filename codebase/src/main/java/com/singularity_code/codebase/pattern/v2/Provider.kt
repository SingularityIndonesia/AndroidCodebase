package com.singularity_code.codebase.pattern.v2

import arrow.core.Option
import com.singularity_code.codebase.pattern.Payload
import com.singularity_code.codebase.util.serialization.ErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

/**
 * Created by: stefanus
 * 27/08/23 08.57
 * Design by: stefanus.ayudha@gmail.com
 */

interface Provider<P : Payload, D> {

    val loading: Flow<Boolean>

    val success: Flow<Option<D>>

    val error: Flow<Option<ErrorMessage>>

    val operator: suspend (P) -> Result<D>

    fun update(
        payload: P
    )
}