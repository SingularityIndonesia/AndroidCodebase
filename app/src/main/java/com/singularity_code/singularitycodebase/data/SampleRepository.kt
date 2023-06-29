package com.singularity_code.singularitycodebase.data

import arrow.core.Either
import com.singularity_code.codebase.util.ErrorMessage
import com.singularity_code.singularitycodebase.data.model.SampleResult
import com.singularity_code.singularitycodebase.data.payload.GetSamplePLD
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    suspend fun getSample(pld: GetSamplePLD): Flow<Either<ErrorMessage, SampleResult>>
}