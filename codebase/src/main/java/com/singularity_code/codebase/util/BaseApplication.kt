package com.singularity_code.codebase.util

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.datastore.pref.PlutoDatastorePreferencesPlugin
import com.pluto.plugins.exceptions.PlutoExceptions
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.logger.PlutoTimberTree
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.preferences.PlutoSharePreferencesPlugin
import timber.log.Timber

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        preparePluto()
    }

    private fun preparePluto() {
        Pluto.Installer(this)
            .addPlugin(PlutoNetworkPlugin("exceptions"))
            .apply {
                addPlugin(PlutoExceptionsPlugin("exceptions"))

                PlutoExceptions.setExceptionHandler { thread, throwable ->
                    Timber.tag("Exception")
                        .w("uncaught exception handled on thread: ${thread.name} $throwable")
                }

                PlutoExceptions.setANRHandler { thread, exception ->
                    Timber.tag("ANR")
                        .d(exception, "potential ANR detected on thread: ${thread.name} $exception")
                }

                PlutoExceptions.mainThreadResponseThreshold = 10_000
            }
            .apply {
                addPlugin(PlutoLoggerPlugin("logger"))
                Timber.plant(PlutoTimberTree());
            }
            .apply {
                addPlugin(PlutoSharePreferencesPlugin("sharedPref"))
                // DB_NAME should be same as database name assigned while creating the database.
                // PlutoRoomsDBWatcher.watch(DB_NAME, SampleDatabase::class.java)
            }
            .apply {
                addPlugin(PlutoDatastorePreferencesPlugin("datastore"))
                // val Context.appPreferences by preferencesDataStore(
                //    name = PREF_NAME
                // )
                //
                // PlutoDatastoreWatcher.watch(PREF_NAME, appPreferences)
            }
            .install()

    }
}