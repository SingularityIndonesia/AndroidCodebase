package com.singularity_code.codebase.util.serialization

val Throwable.errorMessage : ErrorMessage
    get() = this.localizedMessage
        ?: this.message
        ?: this.cause?.localizedMessage
        ?: this.cause?.message
        ?: "Unknown Error"

const val UNKNOWN_ERROR = "Unknown Error"