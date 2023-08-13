package com.singularity_code.codebase.util.ext

import com.singularity_code.codebase.util.Singularity


val Singularity.plutoDebuggerIsEnabled
    get() = enabledFeature
        .count { it is Singularity.Feature.PLUTO_DEBUGGER } > 0