package com.singularity_code.codebase.util.debug.chucker

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.RetentionManager
import com.singularity_code.codebase.util.Singularity
import okhttp3.Interceptor
import com.chuckerteam.chucker.api.ChuckerInterceptor as Chucker

// Create the Collector
val chuckerCollector = ChuckerCollector(
    context = Singularity.application,
    // Toggles visibility of the notification
    showNotification = true,
    // Allows to customize the retention period of collected data
    retentionPeriod = RetentionManager.Period.ONE_HOUR
)

// Create the Interceptor
val ChuckerInterceptor: Interceptor = Chucker.Builder(Singularity.application)
    // The previously created Collector
    .collector(chuckerCollector)
    // The max body content length in bytes, after this responses will be truncated.
    .maxContentLength(250_000L)
    // List of headers to replace with ** in the Chucker UI
    .redactHeaders("Auth-Token", "Bearer")
    // Read the whole response body even when the client does not consume the response completely.
    // This is useful in case of parsing errors or when the response body
    // is closed before being read like in Retrofit with Void and Unit types.
    .alwaysReadResponseBody(true)
    // Use decoder when processing request and response bodies. When multiple decoders are installed they
    // are applied in an order they were added.
    // .addBodyDecoder(decoder)
    // Controls Android shortcut creation.
    .createShortcut(true)
    .build()
