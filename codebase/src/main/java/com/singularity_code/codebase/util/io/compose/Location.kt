package com.singularity_code.codebase.util.io.compose

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetLocation(
    onLocationResult: (LocationResult) -> Unit
) {
    val activity = LocalContext.current as? Activity

    if (activity != null) {
        val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                onLocationResult.invoke(p0)
            }
        }.also {
            val locationRequest = LocationRequest.create().apply {
                interval = 10000
                fastestInterval = 5000
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }

            if (!permissionState.hasPermission)
                activity.requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    100
                )
            else
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    it,
                    Looper.getMainLooper()
                )
        }
    }
}