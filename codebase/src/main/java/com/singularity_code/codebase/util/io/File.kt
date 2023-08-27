package com.singularity_code.codebase.util.io

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.core.content.FileProvider
import arrow.core.Either
import arrow.core.Either.Right
import arrow.core.Either.Left
import com.singularity_code.codebase.util.serialization.ErrorMessage
import com.singularity_code.codebase.util.serialization.UNKNOWN_ERROR
import java.io.*
import java.text.SimpleDateFormat
import java.util.Date

fun getJPEGImageFromUri(
    context: Context,
    qualityInPercent: Int,
    uri: Uri
): File {
    val pngFile: File = context.createImageFileJPG()
    val contentResolver: ContentResolver = context.contentResolver
    val inputStream: InputStream? = contentResolver.openInputStream(uri)
    val jpegBitmap = BitmapFactory.decodeStream(inputStream)

    if (jpegBitmap != null) {
        val outputStream = FileOutputStream(pngFile)

        try {
            jpegBitmap.compress(Bitmap.CompressFormat.JPEG, qualityInPercent, outputStream)
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

enum class FileType(
    val mediaType: String
) {
    PNG_IMAGE(
        "image/png"
    ),
    JPEG_IMAGE(
        "image/jped"
    ),
    UNKNOWN("")
}

val File.fileType: FileType
    get() = this.path.let {
        it.substring(it.length - 6, it.length - 1)
    }.let {
        when {
            it.contains(".png", true) -> FileType.PNG_IMAGE
            it.contains(".jpeg", true)
                    || it.contains(".jpg", true) -> FileType.JPEG_IMAGE

            else -> FileType.UNKNOWN
        }
    }

/**
 *
 * Add the following to your manifest:
 * <provider
 *     android:name="androidx.core.content.FileProvider"
 *     android:authorities="${applicationId}.provider"
 *     android:exported="false"
 *     android:grantUriPermissions="true">
 *     <meta-data
 *         android:name="android.support.FILE_PROVIDER_PATHS"
 *         android:resource="@xml/my_app_provider" />
 * </provider>
 *
 * Make xml file forexample : my_app_provider.xml, with following pattern
 * <paths xmlns:android="http://schemas.android.com/apk/res/android">
 *     <files-path name="my_files" path="files/" />
 *     <external-path name="download" path="Download/{AppName}/" />
 * </paths>
 */
@Deprecated(
    "this warning is just to make sure you have setup the file provider. " +
            "Expose this function to see example of file provider setup"
)
fun writeToFileInExternalProviderDirectory(
    context: Context,
    fileName: String,
    fileContent: String,
    subDir: String = "",
    notifyMediaScanner: Boolean = true
): Either<ErrorMessage, File> {
    val downloadsDir = File(context.getExternalFilesDir(null), subDir)
    if (!downloadsDir.exists()) {
        downloadsDir.mkdirs() // Create the directory if it doesn't exist
    }

    val file = File(downloadsDir, fileName)

    return try {
        val writer = FileWriter(file)
        writer.append(fileContent)
        writer.flush()
        writer.close()

        if (notifyMediaScanner)
            notifyMediaScannerForANewFile(context, file)

        Either.Right(file)
    } catch (e: IOException) {
        e.printStackTrace()
        Either.Left(e.localizedMessage ?: e.message ?: UNKNOWN_ERROR)
    }
}

fun notifyMediaScannerForANewFile(
    context: Context,
    file: File
) {
    MediaScannerConnection.scanFile(
        context,
        arrayOf<String>(file.absolutePath),
        null
    ) { _, _ ->
        // Scan completed, you can perform any additional actions here
    }
}

@SuppressLint("QueryPermissionsNeeded")
fun openFileWithIntent(
    context: Context,
    file: File,
    mimeType: String?,
    providerAuthority: String
): Either<ErrorMessage, Uri> {

    val contentUri = runCatching {
        FileProvider.getUriForFile(context, providerAuthority, file)
            .let(::Right)
    }.getOrElse {
        Either.Left(it.localizedMessage ?: it.message ?: UNKNOWN_ERROR)
    }

    val uriIsValid = contentUri
        .fold(
            ifLeft = { false },
            ifRight = {
                isUriValid(context, it)
            }
        )

    if (!uriIsValid) return Left("Uri invalid")

    val openIntent = contentUri.map { uri ->
        Intent(Intent.ACTION_VIEW).apply {
            data = uri
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            mimeType?.let { mime ->
                setDataAndType(uri, mime)
            }
        }
    }

    val result = openIntent.fold(
        ifRight = {
            if (it.resolveActivity(context.packageManager) == null)
                Left("No installed apps can open this kind of file")
            else {
                context.startActivity(it)
                contentUri
            }
        },
        ifLeft = {
            Left(it)
        }
    )

    return result
}

fun isUriValid(
    context: Context,
    uri: Uri
): Boolean {
    return try {
        val resolver = context.contentResolver
        val inputStream = resolver.openInputStream(uri)
        inputStream?.close()
        true
    } catch (e: Exception) {
        false
    }
}