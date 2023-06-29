# Singularity Codebase
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

# Features
- [Provider](docs/Provider.md)
- [Pluto Debugger](https://androidpluto.com/)
- [OkHttp](docs/OkHttp.md)
- [Retrofit Util](docs/Retrofit.md)
- [BaseApplication] (codebase/src/main/java/com/singularity_code/codebase/util/BaseApplication.kt).
  The base application is integrated with Pluto Debugger.
- [Kotlin Arrow](https://arrow-kt.io/).
  Arrow provides functional programming pattern to your code.
- [Gson](https://github.com/google/gson)
- [LazyFunction](docs/LazyFunction.md)
- [Coroutine Util](docs/Coroutine.md)
- [Permission Util](docs/Permission.md)
- [State Collectors](docs/StateCollector.md)

# Note
Note that this example was made as simple as possible, so it is not recommended to follow the architecture.

# Author
[Stefanus Ayudha](https://github.com/stefanusayudha)
