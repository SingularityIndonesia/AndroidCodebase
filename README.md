# Singularity Codebase
This is the code base that we develop to help everyone in creating android apps.
This codebase provides tools that is necessary to use in android development process.
This codebase is free to use and distribute under MIT licence.

# Why Using Singularity Codebase
- No Abstraction (Every body hates abstraction).
- Everything function. We provide functions not classes to simplify. Don't bother to make classes.
- Everything lazy. We provide builder functions as lazy, so you can use it with `by` keywords and initiate it lazily.
- Builder Pattern and Strategy Pattern (every body loves them).
- We are targeting MVI and state automation with minimum lines of codes. You can still using MVVM and it's hightly recomended to combine it with MVI to get the maximum benefit.

# Implementation
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.SingularityIndonesia:AndroidCodebase:1.7.2'
}
```

# Initialization
```kotlin
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Singularity
            .Init(this)
            .EnableFeature(
                Singularity.Feature.PLUTO_DEBUGGER,
                Singularity.Feature.MULTI_DEX
            )
    }
}
```

# Features
- [Provider](docs/Provider.md)
- [Pluto Debugger](https://androidpluto.com/)
- [OkHttp](docs/OkHttp.md)
- [Retrofit Util](docs/Retrofit.md)
- [Kotlin Arrow](https://arrow-kt.io/).
  Arrow provides functional programming pattern to your code.
- [Gson](https://github.com/google/gson)
- [LazyFunction](docs/LazyFunction.md)
- [Coroutine Util](docs/Coroutine.md)
- [Permission Util](docs/Permission.md)
- [State Collectors](docs/StateCollector.md)
- [Room Database Util](docs/Room.md)
- [Encrypted Room Database](docs/EncryptedRoom.md)
- [Biometric Util](docs/Biometric.md)

# Note
Note that this example was made as simple as possible, so it is not recommended to follow the architecture.

We recomend you to use the MVVM+MVI together with State Automation Pattern like the example [here](app/src/main/java/com/singularity_code/singularitycodebase/ui/activity/providerdemo/ProviderDemoViewModel.kt), to reduce code diversity and eliminate side effects.
By following that pattern you will no longer need to fight with spagetty flow and overlapping side effects. This pattern is exclusively being part of this codebase design.
But be ware that MVI can be pretty slow in big data processing and in case you need more speed we recommend you to use pure but limitted MVVM patter

# Author
[Stefanus Ayudha](https://github.com/stefanusayudha)

# Keep It Simple Stupid
