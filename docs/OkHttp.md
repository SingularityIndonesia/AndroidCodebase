You can create HTTP Client via `createHttpClient` command. This command creates OkHttpClients that integrated with Pluto debugger.

Ex:
```kotlin
val okhttpClient = createHttpClient(
    interceptors = listOf(
        customInterceptor1
    )
)

// Or via delegation function
val okHttpClient by httpClient(
    interceptors = listOf(
        customInterceptor1
    )
)
```