```kotlin
val updater = lazyFunction {
    val isLoading = sampleProvider.loading.first()
    if (isLoading) {
        flow.emit("Loading..")
        return@lazyFunction
    }

    val success = sampleProvider.success.first()
    if (success.first) {
        flow.emit("Success : ${success.second}")
        return@lazyFunction
    }

    val failed = sampleProvider.failed.first()
    if (failed.first) {
        flow.emit("Failed : ${failed.second}")
        return@lazyFunction
    }
}

CoroutineScope().launch{
    updater.tryInvoke(
        signature = "Unix Signature Here ${java.lang.System.currentTimeMillis()}"
    )
}
```

Lazy function is similar to stateflow. The block of lazy function is lazily executed and it will ignore rapid invocation if it's still busy,
but the last invocation will always be executed. But you also can force the function to invoke immediately by calling `someLazyFunction.forceInvoke(..)`.

