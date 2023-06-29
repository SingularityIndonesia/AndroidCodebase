package com.singularity_code.singularitycodebase.data.payload

import com.singularity_code.codebase.pattern.Payload
import com.singularity_code.codebase.util.QueryMap

data class GetSamplePLD(
    val credential: String
): Payload {
    override val queries: QueryMap = hashMapOf(
        "credential" to credential
    )
}