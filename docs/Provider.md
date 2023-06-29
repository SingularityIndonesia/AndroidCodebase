Ex:

```kotlin
class MainViewModel(
    private val repo: Repository
): ViewModel() {
    
    val dataProvider by provider(repo::method)
    
}
```