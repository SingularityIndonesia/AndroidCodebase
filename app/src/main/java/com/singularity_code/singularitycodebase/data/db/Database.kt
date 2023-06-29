package com.singularity_code.singularitycodebase.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.singularity_code.singularitycodebase.data.db.dao.UserDao
import com.singularity_code.singularitycodebase.data.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}
