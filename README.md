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

## 8. Gson

## 9. LazyFunction
Lazy function is similar to stateflow. The block of lazy function is lazily executed, it will ignore rapid invocation if it's still busy,
but the last invocation will always be executed. But you also can force the function to invoke immediately by calling `someLazyFunction.forceInvoke(..)`.

# Implementation
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.SingularityIndonesia:AndroidCodebase:1.3.0'
}
```

# Note
Note that this example was made as simple as possible, so it is not recommended to follow the architecture.

# Author
[Stefanus Ayudha](https://github.com/stefanusayudha)
