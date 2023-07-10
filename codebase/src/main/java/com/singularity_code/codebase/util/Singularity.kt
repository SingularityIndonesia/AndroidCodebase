package com.singularity_code.codebase.util

import android.app.Application
import androidx.multidex.MultiDex


object Singularity {
    enum class Feature {
        PLUTO_DEBUGGER,

        @Deprecated(
            "Don't worry, just want to make sure that you set `android.defaultConfig.multiDexEnabled = true` "
                    + "in your app build.gradle. "
                    + "If you did, you can ignore this warning."
        )
        MULTI_DEX
    }

    lateinit var application: Application

    @JvmStatic
    fun Init(application: Application): Singularity {
        this.application = application
        return this
    }

    fun EnableFeature(
        vararg features: Feature
    ): Singularity {

        if (features.contains(Feature.MULTI_DEX))
            MultiDex.install(application.applicationContext)

        if (features.contains(Feature.PLUTO_DEBUGGER))
            preparePluto(application)

        return this
    }

}