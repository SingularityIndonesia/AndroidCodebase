package com.singularity_code.singularitycodebase.data

import arrow.retrofit.adapter.either.ResponseE
import com.singularity_code.singularitycodebase.data.exclusive.ErrorWrapper
import com.singularity_code.singularitycodebase.domain.model.SampleResult
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SampleApi {

    @GET("somewhere")
    suspend fun getSampleData(
        @QueryMap queries: HashMap<String,String>
    ): ResponseE<ErrorWrapper, SampleResult>
}