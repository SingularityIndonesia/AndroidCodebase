package com.singularity_code.codebase.util.geospace

import com.google.android.gms.maps.model.LatLng
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

const val EARTH_RADIUS_IN_METERS = 6371000.0 // Earth's radius in meters

@Deprecated(
    "This function is aproximation only. " +
            "Real calculation requires altitude to be accurate since earth is not a smooth globe. " +
            "The error can be extreamly large in some place with distinct altitude."
)
fun calculateDistanceInMeters(
    latLng1: LatLng,
    latLng2: LatLng,
): Double {
    val latDistance = Math.toRadians(latLng2.latitude - latLng1.latitude)
    val lonDistance = Math.toRadians(latLng2.longitude - latLng1.longitude)
    val a = sin(latDistance / 2) * sin(latDistance / 2) +
            cos(Math.toRadians(latLng1.latitude)) * cos(Math.toRadians(latLng2.latitude)) *
            sin(lonDistance / 2) * sin(lonDistance / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return EARTH_RADIUS_IN_METERS * c
}