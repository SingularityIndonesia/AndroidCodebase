package com.singularity_code.singularitycodebase

import android.app.Application
import com.singularity_code.codebase.util.preparePluto

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        preparePluto()
    }
}