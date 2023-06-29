package com.singularity_code.codebase.pattern

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface SimpleDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: T)

    @Delete
    fun delete(user: T)
}