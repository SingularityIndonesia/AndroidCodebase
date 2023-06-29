package com.singularity_code.singularitycodebase.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.singularity_code.codebase.pattern.SimpleDao
import com.singularity_code.singularitycodebase.data.model.User

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