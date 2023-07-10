package com.singularity_code.codebase.util

import java.text.SimpleDateFormat

const val ONE_MINUTE_MILLIS: Long = 60L * 1000L
const val ONE_HOUR_MILLIS: Long = 60L * ONE_MINUTE_MILLIS
const val ONE_DAY_MILLIS: Long = 24L * ONE_HOUR_MILLIS

/** String date to epoc.
 * Default pattern : "yyyy-MM-dd'T'HH:mm:ss.SSSZ" (ISO 8601 Standard).
 * **/
fun String.toEpoc(
    inputPattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
): Long = this.let { string ->
    SimpleDateFormat(inputPattern)
        .parse(string)
        ?.time ?: 0L
}