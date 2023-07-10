package com.singularity_code.codebase.util

import com.google.gson.Gson
import com.singularity_code.codebase.pattern.JsonConvertible

inline fun <reified T : JsonConvertible> String.toObject(): T =
    Gson().fromJson(this, T::class.java)