package com.singularity_code.codebase.pattern

import com.google.gson.Gson

/** to be used for serialize able object **/
interface JsonConvertible {
    fun toStringJson(): String {
        return Gson().toJson(this)
    }
}