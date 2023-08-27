package com.singularity_code.codebase.util.io

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.gms.maps.model.LatLng

/**
 * Created by: stefanus
 * 27/08/23 20.45
 * Design by: stefanus.ayudha@gmail.com
 */
fun openGoogleMapNavigation(
    target: LatLng,
    activity: Context
) {
    val latitude = target.latitude
    val longitude = target.longitude
    val label = "Destination"
    val uri = "google.navigation:q=$latitude,$longitude&label=$label"
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(uri)
    )
    activity.startActivity(intent)
}