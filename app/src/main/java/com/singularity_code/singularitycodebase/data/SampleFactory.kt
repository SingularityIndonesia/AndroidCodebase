package com.singularity_code.singularitycodebase.data

import arrow.core.Either
import com.singularity_code.codebase.util.ErrorMessage
import com.singularity_code.codebase.util.createHttpClient
import com.singularity_code.codebase.util.retrofitService
import com.singularity_code.singularitycodebase.domain.model.SampleResult
import com.singularity_code.singularitycodebase.domain.payload.GetSamplePLD
import com.singularity_code.singularitycodebase.domain.repo.SampleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleFactory : SampleRepository {

    private val sampleApi: SampleApi by retrofitService(
        service = SampleApi::class.java,
        httpClient = createHttpClient(),
        baseUrl = "http://base.url/api/"
    )

    override suspend fun getSample(
        pld: GetSamplePLD
    ): Flow<Either<ErrorMessage, SampleResult>> {

        return flow {

            delay(5000)

            val result = runCatching {
                sampleApi.getSampleData(
                    pld.queries
                )
            }
                .getOrElse {
                    val error =
                        it.message ?: it.localizedMessage ?: it.cause?.message ?: "unknown error"
                    emit(
                        Either.Left(error)
                    )

                    return@flow
                }.body
                .fold(
                    ifLeft = {
                        Either.Left(it.message)
                    },
                    ifRight = {
                        Either.Right(it)
                    }
                )

            emit(result)
        }
    }
}