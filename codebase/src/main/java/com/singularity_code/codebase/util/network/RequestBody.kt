package com.singularity_code.codebase.util.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun createRequestBody(value: String): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
}

fun createMultipartBodyForJPEG(key: String, image: File): MultipartBody.Part {
    val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), image)
    return MultipartBody.Part.createFormData(key, image.name, requestFile)
}

fun createMultipartBodyForPNG(key: String, image: File): MultipartBody.Part {
    val requestFile = RequestBody.create("image/png".toMediaTypeOrNull(), image)
    return MultipartBody.Part.createFormData(key, image.name, requestFile)
}

fun createMultipartBody(key: String, mediaType: String, file: File): MultipartBody.Part {
    val requestFile = RequestBody.create(mediaType.toMediaTypeOrNull(), file)
    return MultipartBody.Part.createFormData(key, file.name, requestFile)
}