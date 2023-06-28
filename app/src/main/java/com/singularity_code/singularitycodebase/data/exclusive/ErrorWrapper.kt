package com.singularity_code.singularitycodebase.data.exclusive

import com.google.gson.annotations.SerializedName

data class ErrorWrapper(
    @SerializedName("message")
    val message: String = "Error"
)
