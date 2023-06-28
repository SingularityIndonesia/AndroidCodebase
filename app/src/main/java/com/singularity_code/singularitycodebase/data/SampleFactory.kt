package com.singularity_code.singularitycodebase.data

import arrow.core.Either
import com.singularity_code.codebase.util.ErrorMessage
import com.singularity_code.singularitycodebase.domain.model.SampleResult
import com.singularity_code.singularitycodebase.domain.payload.GetSamplePLD
import com.singularity_code.singularitycodebase.domain.repo.SampleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleFactory : SampleRepository {
    override suspend fun getSample(
        pld: GetSamplePLD
    ): Flow<Either<ErrorMessage, SampleResult>> {

        return flow {

            delay(5000)
            val result = SampleResult("Singularity Indonesia")

            emit(Either.Right(result))
        }
    }
}