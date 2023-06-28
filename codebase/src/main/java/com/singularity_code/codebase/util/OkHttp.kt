package com.singularity_code.codebase.util

import com.pluto.plugins.network.PlutoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient

fun createHttpClient(
    interceptors: List<Interceptor> = listOf()
): OkHttpClient = OkHttpClient.Builder()
    .apply {
        interceptors.forEach { interceptor ->
            addInterceptor(interceptor)
        }
    }
    .addInterceptor(PlutoInterceptor())
    .build()

fun httpClient(
    interceptors: List<Interceptor> = listOf()
) = lazy {
    createHttpClient(interceptors)
}