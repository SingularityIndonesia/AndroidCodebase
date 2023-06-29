package com.singularity_code.codebase.util

import android.app.Application


private lateinit var _applicationContext: Application

val applicationContext get() = _applicationContext

fun Application.declareApplicationContext() {
    _applicationContext = this
}