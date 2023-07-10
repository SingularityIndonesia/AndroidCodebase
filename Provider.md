```kotlin
class MainViewModel(
    private val repo: Repository
): ViewModel() {
    
    val dataProvider by provider(repo::method)
    
}
```

Providers util has build in trial function if errors happen, you can set error trial count to set maximum trial before it give up.
See [Provider Util](../codebase/src/main/java/com/singularity_code/codebase/util/Provider.kt)
