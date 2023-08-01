package com.singularity_code.codebase.util.serialization

import com.singularity_code.codebase.util.serialization.ErrorMessage

val Throwable.errorMessage : ErrorMessage
    get() = this.localizedMessage
        ?: this.message
        ?: this.cause?.localizedMessage
        ?: this.cause?.message
        ?: "Unknown Error"