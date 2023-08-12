# Singularity Codebase
This is the code base that we develop to help everyone to create android apps.
This codebase provides collection of tools that is necessary to use in android development process.
This codebase is "abstraction free", provide you non-strict pattern and rules.
This codebase focus on tools that is essentials.
This codebase not overriding super method; This means that we are not making "new method" to call the same effects;
While this have some downside, in dependency implementation, our purpose is to reduce learning curve.
Everything is about Pattern, Builder, and Strategy.
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
    releaseImplementation 'com.github.SingularityIndonesia:AndroidCodebase:1.9.2'
    debugImplementation 'com.github.SingularityIndonesia:AndroidCodebase:1.9.2-debug'
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
- [Provider](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/Provider.md)
- [Pluto Debugger](https://androidpluto.com/)
- [OkHttp](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/OkHttp.md)
- [Retrofit Util](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/Retrofit.md)
- [Kotlin Arrow](https://arrow-kt.io/).
  Arrow provides functional programming pattern to your code.
- [Gson](https://github.com/google/gson)
- [LazyFunction](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/LazyFunction.md)
- [Coroutine Util](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/Coroutine.md)
- [Permission Util](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/Permission.md)
- [State Collectors](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/StateCollector.md)
- [Room Database Util](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/Room.md)
- [Encrypted Room Database](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/EncryptedRoom.md)
- [Biometric Util](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/Biometric.md)

# Architecture Guideline
See : [Architecture Guideline](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/ArchitectureGuideline.md)

# Note
Note that this example was made as simple as possible, so it is not recommended to follow the architecture of the given sample and read the notes.

We recomend you to use the MVVM+MVI together with State Automation Pattern like the example [here](https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/ArchitectureGuideline.md), to reduce code diversity and eliminate side effects.
By following that pattern you will no longer need to fight with spagetty flow and overlapping side effects. This pattern is exclusively being part of this codebase design.
But be ware that MVI can be pretty slow in big data processing and in case you need more speed we recommend you to use pure but limitted MVVM pattern.

# Author
Feel free to chat with me: [Stefanus Ayudha](https://github.com/stefanusayudha)

# Currently Powered by Singularity
<p float="left">
<img src="https://github.com/SingularityIndonesia/AndroidCodebase/blob/docs/image/Logo64.png" width="64" alt="5T Salesman Tracker">
</p>
