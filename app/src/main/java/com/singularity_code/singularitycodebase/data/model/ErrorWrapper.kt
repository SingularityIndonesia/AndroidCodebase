package com.singularity_code.singularitycodebase.data.model

import com.google.gson.annotations.SerializedName

data class ErrorWrapper(
    @SerializedName("message")
    val message: String = "Error"
)
