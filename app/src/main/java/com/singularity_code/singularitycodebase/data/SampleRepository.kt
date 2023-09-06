package com.singularity_code.singularitycodebase.data

import com.singularity_code.codebase.util.network.createHttpClient
import com.singularity_code.codebase.util.network.retrofitService
import com.singularity_code.singularitycodebase.data.model.SampleResult
import com.singularity_code.singularitycodebase.data.payload.GetSamplePLD
import com.singularity_code.singularitycodebase.data.web.SampleApi
import kotlinx.coroutines.delay

class SampleRepository {

    /**
     * Note that im giving you example as simple as possible, so it is less too you to get through the mechanism;
     * Therefore the architecture might not be satisfying.
     * But of course for the real world execution, I recommend you to use prefer architecture such Clean Architecture or such.
     */
    private val sampleApi: SampleApi by retrofitService(
        service = SampleApi::class.java,
        httpClient = createHttpClient(),
        baseUrl = "http://base.url/api/"
    )

    suspend fun getSample(
        pld: GetSamplePLD
    ): Result<SampleResult> =
        runCatching {
            // emulate delay
            delay(3000)

            sampleApi.getSampleData(
                pld.queries
            )
        }
}