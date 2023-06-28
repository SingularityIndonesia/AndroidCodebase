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
## 2. Pluto
## 3. OkHttp
You can create HTTP Client via `createHttpClient` command. This command creates OkHttpClients that integrated with Pluto debugger.
## 4. BaseApplication 
The base application is integrated with Pluto Debugger.
## 5. Easy State Collector 
You can easily collect state inside Activity / Fragment / ViewModel by simply calling:
```kotlin
collect(state) { value ->
    // Do something
}
```
## 6. Kotlin Arrow
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
