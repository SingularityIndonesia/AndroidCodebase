package com.singularity_code.codebase.util.debug.pluto

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

fun preparePluto(
    application: Application
) {

    Pluto.Installer(application)
        .addPlugin(PlutoNetworkPlugin("exceptions"))
        .addPlugin(PlutoExceptionsPlugin("exceptions"))
        .addPlugin(PlutoDatastorePreferencesPlugin("datastore"))
        .addPlugin(PlutoLoggerPlugin("logger"))
        .addPlugin(PlutoSharePreferencesPlugin("sharedPref"))
        .install()

    Timber.plant(PlutoTimberTree());

    PlutoExceptions.mainThreadResponseThreshold = 10_000

    // FIXME:
    //  ISSUE = Crash; Pluto not initialized.
    //  Something wrong with pluto initialization.
    //  Pluto library suspected.

//    PlutoExceptions.setANRHandler { thread, exception ->
//        Timber.tag("ANR")
//            .d(exception, "potential ANR detected on thread: ${thread.name} $exception")
//    }
//        PlutoExceptions.setExceptionHandler { thread, throwable ->
//        Timber.tag("Exception")
//            .w("uncaught exception handled on thread: ${thread.name} $throwable")
//    }

}