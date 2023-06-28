package com.singularity_code.singularitycodebase.domain.repo

import arrow.core.Either
import com.singularity_code.codebase.util.ErrorMessage
import com.singularity_code.singularitycodebase.domain.model.SampleResult
import com.singularity_code.singularitycodebase.domain.payload.GetSamplePLD
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    suspend fun getSample(pld: GetSamplePLD): Flow<Either<ErrorMessage, SampleResult>>
}