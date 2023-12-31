package com.singularity_code.codebase.util.network

import androidx.annotation.NonNull
import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <I> createRetrofitService(
    @NonNull service: Class<I>,
    httpClient: OkHttpClient,
    baseUrl: String,
): I = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(baseUrl)
    .addCallAdapterFactory(EitherCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(service)

fun <I> retrofitService(
    @NonNull service: Class<I>,
    httpClient: OkHttpClient,
    baseUrl: String,
) = lazy {
    createRetrofitService(
        service,
        httpClient,
        baseUrl
    )
}