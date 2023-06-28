package com.singularity_indonesia.codebase.pattern

import com.singularity_indonesia.codebase.util.FieldMap
import com.singularity_indonesia.codebase.util.QueryMap
import okhttp3.RequestBody

interface Payload {
    val fields: FieldMap get() = TODO()
    val queries: QueryMap get() = TODO()
    val body: RequestBody get() = TODO()
}