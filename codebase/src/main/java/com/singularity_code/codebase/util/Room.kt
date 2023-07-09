package com.singularity_code.codebase.util

import androidx.room.Room
import androidx.room.RoomDatabase

/** DB Factory **/
inline fun <reified D : RoomDatabase> db() = lazy {
    Room.databaseBuilder(
        context = Singularity.application,
        klass = D::class.java,
        name = D::class.simpleName
    ).build()
}