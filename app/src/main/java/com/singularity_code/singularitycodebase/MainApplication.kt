package com.singularity_code.singularitycodebase

import com.singularity_code.codebase.SingularityApp

class MainApplication : SingularityApp() {
    override val enabledFeature: List<Feature> =
        listOf(
            Feature.PLUTO_DEBUGGER,
            Feature.MULTI_DEX
        )
}