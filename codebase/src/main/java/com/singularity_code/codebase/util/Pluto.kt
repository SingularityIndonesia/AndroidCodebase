package com.singularity_code.codebase.util

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.datastore.pref.PlutoDatastorePreferencesPlugin
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.logger.PlutoTimberTree
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.preferences.PlutoSharePreferencesPlugin
import timber.log.Timber

fun Application.preparePluto() {
    Pluto.Installer(this)
        .addPlugin(PlutoNetworkPlugin("exceptions"))
        .addPlugin(PlutoExceptionsPlugin("exceptions"))
        .addPlugin(PlutoDatastorePreferencesPlugin("datastore"))
        .addPlugin(PlutoLoggerPlugin("logger"))
        .addPlugin(PlutoSharePreferencesPlugin("sharedPref"))
        .install()


    //        PlutoExceptions.setExceptionHandler { thread, throwable ->
    //            Timber.tag("Exception")
    //                .w("uncaught exception handled on thread: ${thread.name} $throwable")
    //        }
    //
    //        PlutoExceptions.setANRHandler { thread, exception ->
    //            Timber.tag("ANR")
    //                .d(exception, "potential ANR detected on thread: ${thread.name} $exception")
    //        }
    //        PlutoExceptions.mainThreadResponseThreshold = 10_000

    Timber.plant(PlutoTimberTree());

    // val Context.appPreferences by preferencesDataStore(
    //    name = PREF_NAME
    // )
    //
    // PlutoDatastoreWatcher.watch(PREF_NAME, appPreferences)

}