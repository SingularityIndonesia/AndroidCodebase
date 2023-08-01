package com.singularity_code.codebase.util.io

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*


fun Context.createImageFileJPG(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

fun Context.createImageFilePNG(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "PNG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".png", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

fun convertJpgToPng(
    context: Context,
    jpegUri: Uri,
    qualityInPercent: Int = 100
): File {

    val pngFile: File = context.createImageFilePNG()
    val contentResolver: ContentResolver = context.contentResolver
    val inputStream: InputStream? = contentResolver.openInputStream(jpegUri)
    val jpegBitmap = BitmapFactory.decodeStream(inputStream)

    if (jpegBitmap != null) {
        val outputStream = FileOutputStream(pngFile)

        try {
            jpegBitmap.compress(Bitmap.CompressFormat.PNG, qualityInPercent, outputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            outputStream.flush()
            outputStream.close()
        }
    } else {
        // Handle the case when decoding the JPEG fails
        // For example, the file might not exist or might not be a valid image.
        throw Error("File not found")
    }

    return pngFile
}
