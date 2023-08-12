package com.singularity_code.codebase.util.network

import com.pluto.plugins.network.PlutoInterceptor
import com.singularity_code.codebase.util.Singularity
import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun createHttpClient(
    interceptors: List<Interceptor> = listOf()
): OkHttpClient = OkHttpClient.Builder()
    .apply {
        interceptors.forEach { interceptor ->
            addInterceptor(interceptor)
        }
        if (Singularity.enabledFeature.contains(Singularity.Feature.PLUTO_DEBUGGER))
            addInterceptor(PlutoInterceptor())
    }
    .build()

fun httpClient(
    interceptors: List<Interceptor> = listOf()
) = lazy {
    createHttpClient(interceptors)
}