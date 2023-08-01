package com.singularity_code.codebase.util.io.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.singularity_code.codebase.util.Singularity

/** DB Factory **/
inline fun <reified D : RoomDatabase> db() = lazy {
    Room.databaseBuilder(
        context = Singularity.application,
        klass = D::class.java,
        name = D::class.simpleName
    ).build()
}