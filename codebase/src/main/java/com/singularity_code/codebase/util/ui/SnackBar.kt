package com.singularity_code.codebase.util.ui

import com.singularity_code.codebase.pattern.material3.SnackBarVisual
import com.singularity_code.codebase.util.serialization.ErrorMessage

fun errorSnackBar(
    message: ErrorMessage
) =
    object : SnackBarVisual.ErrorSnackBar {
        override val message: String
            get() = message
    }

fun normalSnackBar(
    message: ErrorMessage
) =
    object : SnackBarVisual.NormalSnackBar {
        override val message: String
            get() = message
    }