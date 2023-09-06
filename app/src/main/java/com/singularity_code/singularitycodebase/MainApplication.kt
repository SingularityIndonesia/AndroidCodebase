package com.singularity_code.singularitycodebase

import android.app.Application
import com.singularity_code.codebase.util.Singularity

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Singularity
            .Init(this)
            .EnableFeature(
                // note, pluto debugger not available yet in demo,
                // but in your code, you can use it by adding pluto module in your project and uncomment this line.
                // also make sure to implement codebase debug flavour in your project.
                /*Singularity.Feature.PLUTO_DEBUGGER(),*/
                Singularity.Feature.MULTI_DEX()
            )
    }
}