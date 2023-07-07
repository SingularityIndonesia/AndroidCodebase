package com.singularity_code.codebase

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.singularity_code.codebase.util.declareApplicationContext
import com.singularity_code.codebase.util.preparePluto

open class SingularityApp : Application() {
    enum class Feature {
        PLUTO_DEBUGGER,

        @Deprecated(
            "Don't worry, just want to make sure that you set `android.defaultConfig.multiDexEnabled = true` "
                    + "in your app build.gradle. "
                    + "If you did, you can ignore this warning."
        )
        MULTI_DEX
    }

    open val enabledFeature: List<Feature> = listOf()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        declareApplicationContext()
    }

    override fun onCreate() {
        super.onCreate()
        initMultiDex()
        initPluto()
    }

    private fun initMultiDex() {
        if (enabledFeature.contains(Feature.MULTI_DEX))
            MultiDex.install(this)
    }

    private fun initPluto() {
        if (enabledFeature.contains(Feature.PLUTO_DEBUGGER)) {
            preparePluto()
        }
    }

}