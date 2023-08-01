package com.singularity_code.codebase.pattern.material3

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

interface SnackBarVisual {
    interface ErrorSnackBar : SnackbarVisuals {
        override val actionLabel: String
            get() = "OK"
        override val withDismissAction: Boolean
            get() = false
        override val duration: SnackbarDuration
            get() = SnackbarDuration.Indefinite
    }

    interface NormalSnackBar : SnackbarVisuals {
        override val actionLabel: String
            get() = "OK"
        override val withDismissAction: Boolean
            get() = false
        override val duration: SnackbarDuration
            get() = SnackbarDuration.Short
    }
}