# Singularity Codebase
This is the code base that we develop to help everyone in creating android apps.
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
    implementation 'com.github.SingularityIndonesia:AndroidCodebase:1.7.0'
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

We recomend you to use the MVVM+MVI and State Automation Pattern like the example [here]() to reduce code diversity and eliminate side effects.
By following that pattern you will no longer need to fight with spagetty flow and overlapping side effects. This pattern is exclusively being part of this codebase design.
But be ware that MVI can be pretty slow in big data processing and in case you need more speed we recommend you to use pure but limitted MVVM pattern.

# Author
[Stefanus Ayudha](https://github.com/stefanusayudha)
