package com.singularity_code.codebase.util.serialization

import com.google.gson.Gson
import com.singularity_code.codebase.pattern.JsonConvertible

inline fun <reified T : JsonConvertible> String.toObject(): T =
    Gson().fromJson(this, T::class.java)