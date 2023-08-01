package com.singularity_code.codebase.pattern

import com.singularity_code.codebase.util.serialization.FieldMap
import com.singularity_code.codebase.util.serialization.QueryMap
import okhttp3.RequestBody

interface Payload {
    val fields: FieldMap get() = TODO()
    val queries: QueryMap get() = TODO()
    val body: RequestBody get() = TODO()
}