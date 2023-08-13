package com.singularity_code.codebase.util

import android.app.Application
import androidx.multidex.MultiDex
import com.pluto.plugins.exceptions.UncaughtANRHandler
import com.singularity_code.codebase.util.debug.pluto.preparePluto
import java.lang.Thread.UncaughtExceptionHandler


object Singularity {
    sealed class Feature {
        class PLUTO_DEBUGGER(
            val anrHandler: UncaughtANRHandler? = null,
            val exceptionHandler: UncaughtExceptionHandler? = null
        ) : Feature()

        @Deprecated(
            "Don't worry, just want to make sure that you set `android.defaultConfig.multiDexEnabled = true` "
                    + "in your app build.gradle. "
                    + "If you did, you can ignore this warning."
        )
        class MULTI_DEX : Feature()
    }

    lateinit var application: Application

    @JvmStatic
    fun Init(application: Application): Singularity {
        this.application = application
        return this
    }

    private var _enabledFeatures = mutableListOf<Feature>()
    val enabledFeature: List<Feature> get() = _enabledFeatures

    fun EnableFeature(
        vararg features: Feature
    ): Singularity {

        features.forEach {
            when (it) {
                is Feature.MULTI_DEX -> {
                    MultiDex.install(application.applicationContext)
                }

                is Feature.PLUTO_DEBUGGER -> {
                    preparePluto(
                        application,
                        it.anrHandler,
                        it.exceptionHandler
                    )
                }
            }
        }

        _enabledFeatures.addAll(features)

        return this
    }

}