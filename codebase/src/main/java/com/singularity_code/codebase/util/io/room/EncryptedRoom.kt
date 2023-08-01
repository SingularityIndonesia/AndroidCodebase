package com.singularity_code.codebase.util.io.room

import androidx.room.Room
import androidx.room.RoomDatabase
import com.singularity_code.codebase.util.Singularity
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory


inline fun <reified D : RoomDatabase> encryptedDb(
    encryptionPassword: String
) = lazy {

    val passphrase: ByteArray = SQLiteDatabase.getBytes(encryptionPassword.toCharArray())
    val factory = SupportFactory(passphrase)

    Room.databaseBuilder(
        context = Singularity.application,
        klass = D::class.java,
        name = D::class.simpleName
    ).openHelperFactory(factory)
        .build()
}