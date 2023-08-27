package com.singularity_code.codebase.util.debug.pluto

import android.app.Application
import com.pluto.Pluto
import com.pluto.plugins.datastore.pref.PlutoDatastorePreferencesPlugin
import com.pluto.plugins.exceptions.PlutoExceptions
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.exceptions.UncaughtANRHandler
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.logger.PlutoTimberTree
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.preferences.PlutoSharePreferencesPlugin
import timber.log.Timber
import java.lang.Thread.UncaughtExceptionHandler

fun preparePluto(
    application: Application,
    anrHandler: UncaughtANRHandler? = null,
    exceptionHandler: UncaughtExceptionHandler? = null
) {

    Pluto.Installer(application)
        .addPlugin(PlutoNetworkPlugin("network"))
        .addPlugin(PlutoExceptionsPlugin("exceptions"))
        .addPlugin(PlutoDatastorePreferencesPlugin("datastore"))
        .addPlugin(PlutoLoggerPlugin("logger"))
        .addPlugin(PlutoSharePreferencesPlugin("sharedPref"))
        .install()

    Timber.plant(PlutoTimberTree());

    PlutoExceptions.mainThreadResponseThreshold = 10_000

    PlutoExceptions.setANRHandler(
        anrHandler ?: UncaughtANRHandler { thread, exception ->
            Timber.tag("ANR")
                .d(exception, "potential ANR detected on thread: ${thread.name} $exception")
        }
    )
    PlutoExceptions.setExceptionHandler(
        exceptionHandler ?: UncaughtExceptionHandler { thread, throwable ->
            Timber.tag("Exception")
                .w("uncaught exception handled on thread: ${thread.name} $throwable")
        }
    )

}