package com.singularity_code.singularitycodebase.data

import arrow.core.Either
import com.singularity_code.codebase.util.network.createHttpClient
import com.singularity_code.codebase.util.network.retrofitService
import com.singularity_code.codebase.util.serialization.ErrorMessage
import com.singularity_code.singularitycodebase.data.model.SampleResult
import com.singularity_code.singularitycodebase.data.payload.GetSamplePLD
import com.singularity_code.singularitycodebase.data.web.SampleApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SampleRepository {

    /**
     * Note that im giving you example as simple as possible so it is less too you to get trough the mechanism;
     * There fore the architecture might not be satisfying.
     * But of course for the real world execution, i recommend you to use prefer architecture such Clean Architecture or such.
     */
    private val sampleApi: SampleApi by retrofitService(
        service = SampleApi::class.java,
        httpClient = createHttpClient(),
        baseUrl = "http://base.url/api/"
    )

    suspend fun getSample(
        pld: GetSamplePLD
    ): Flow<Either<ErrorMessage, SampleResult>> {

        return flow {

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