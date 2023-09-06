package com.singularity_code.singularitycodebase.data.web

import arrow.retrofit.adapter.either.ResponseE
import com.singularity_code.singularitycodebase.data.model.ErrorWrapper
import com.singularity_code.singularitycodebase.data.model.SampleResult
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SampleApi {

    @GET("somewhere")
    suspend fun getSampleData(
        @QueryMap queries: HashMap<String,String>
    ): SampleResult
}