We have extensions function to collect state inside a fragment, activity and viewmodel.

You can easily collect state inside Activity / Fragment / ViewModel by simply calling:
```kotlin
collect(state) { value ->
    // Do something
}
```

See: [State Collector](../codebase/src/main/java/com/singularity_code/codebase/util/StateCollector.kt)