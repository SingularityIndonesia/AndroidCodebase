package com.singularity_code.codebase.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

fun Context.checkPermission(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun Context.hasPermissions(permissions: List<String>): Boolean {
    return permissions.fold(true) { l, r ->
        l && checkPermission(r)
    }
}