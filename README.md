# Singularity Codebase

# Features
## 1. Provider
You can easily create provider this way (in viewmodel):
```kotlin
class MainViewModel(
    private val repo: Repository
): ViewModel() {
    
    val dataProvider by provider(repo::method)
    
}
```

## 2. Pluto Debugger

## 3. OkHttp
You can create HTTP Client via `createHttpClient` command. This command creates OkHttpClients that integrated with Pluto debugger.

## 4. Retrofit
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
```

## 5. BaseApplication 
The base application is integrated with Pluto Debugger.

## 6. Easy State Collector 
You can easily collect state inside Activity / Fragment / ViewModel by simply calling:
```kotlin
collect(state) { value ->
    // Do something
}
```

## 7. Kotlin Arrow
Arrow provides functional programming pattern to your code.



# Implementation
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.SingularityIndonesia:AndroidCodebase:1.1.0'
}
```

# Author
[Stefanus Ayudha](https://github.com/stefanusayudha)
