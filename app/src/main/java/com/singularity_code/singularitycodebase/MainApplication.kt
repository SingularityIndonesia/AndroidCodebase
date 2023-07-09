package com.singularity_code.singularitycodebase

import android.app.Application
import com.pluto.Pluto
import com.singularity_code.codebase.util.Singularity
import com.singularity_code.codebase.util.preparePluto

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Singularity
            .Init(this)
            .EnableFeature(
                Singularity.Feature.PLUTO_DEBUGGER,
                Singularity.Feature.MULTI_DEX
            )
    }
}