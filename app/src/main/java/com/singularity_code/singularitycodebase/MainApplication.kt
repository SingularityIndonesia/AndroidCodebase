package com.singularity_code.singularitycodebase

import android.app.Application
import android.content.Context
import com.singularity_code.codebase.util.declareApplicationContext
import com.singularity_code.codebase.util.preparePluto

class MainApplication : Application() {

    override fun attachBaseContext(
        base: Context?
    ) {
        super.attachBaseContext(base)
        declareApplicationContext()
    }

    override fun onCreate() {
        super.onCreate()
        preparePluto()
    }
}