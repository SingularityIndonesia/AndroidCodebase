## Creating Database
Create database class and the dao like the following:

```kotlin
@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class MyEncryptedDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}



/** the SimpleDao pattern provides you some basic database functional such insert and delete **/
@Dao
interface UserDao : SimpleDao<User> {

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("SELECT * FROM User WHERE uid IN (:userIds)")
    fun loadAllByIds(
        userIds: IntArray
    ): List<User>

    @Query(
        "SELECT * FROM User WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(
        first: String,
        last: String
    ): User

}
```


## Ready to Use
create or bind database instance via single function :
```kotlin
val db by encryptedDb<MyEncryptedDatabase>()
```

## See:
- [Room Util](../codebase/src/main/java/com/singularity_code/codebase/util/Room.kt)
- [SimpleDao Pattern](../codebase/src/main/java/com/singularity_code/codebase/pattern/SimpleDao.kt)
