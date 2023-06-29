You can create Retrofit instance via `createRetrofitService` function. This function creates retrofit service with EitherCallAdapterFactory,
so we recommend you using ResponseEither in your WebApi pattern.

Ex:
```kotlin
interface WebApi {
    @FormUrlEncoded
    @POST("v1/login")
    suspend fun login(
        @FieldMap fields: Fields
    ): ResponseE<ErrorResponse, LoginData>
}

val apiService = createRetrofitService(
    service = WebApi::class.java,
    httpClient = createHttpClient(),
    baseUrl = "http://..."
)

// Or via delegation
val apiService by retrofitService(
    service = WebApi::class.java,
    httpClient = createHttpClient(),
    baseUrl = "http://..."
)
```