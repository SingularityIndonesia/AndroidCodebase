package com.singularity_code.singularitycodebase.domain.model

import com.google.gson.annotations.SerializedName

data class SampleResult(
    @SerializedName("value")
    val value: String = "Singularity Indonesia"
)