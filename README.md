# Singularity Codebase
This is the code base that we develop to help everyone to create android apps.
This codebase provides tools that is necessary to use in android development process.
This codebase is free to use and distribute under MIT licence.


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

# Architecture Guideline
See : [Architecture Guideline](docs/ArchitectureGuideline.md)

# Note
Note that this example was made as simple as possible, so it is not recommended to follow the architecture of the given sample and read the notes.

We recomend you to use the MVVM+MVI together with State Automation Pattern like the example [here](docs/ArchitectureGuideline.md), to reduce code diversity and eliminate side effects.
By following that pattern you will no longer need to fight with spagetty flow and overlapping side effects. This pattern is exclusively being part of this codebase design.
But be ware that MVI can be pretty slow in big data processing and in case you need more speed we recommend you to use pure but limitted MVVM pattern.

# Author
Feel free to chat with me: [Stefanus Ayudha](https://github.com/stefanusayudha)

# Currently Powered by Singularity
<p float="left">
<img src="https://github.com/SingularityIndonesia/AndroidCodebase/blob/main/docs/image/Logo64.png" width="64" alt="5T Salesman Tracker">
</p>
