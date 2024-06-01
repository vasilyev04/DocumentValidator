package com.vasilyev.documentvalidator.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Base64
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun createMultiPartFile(file: File, name: String): MultipartBody.Part{
    val requestBody = file.asRequestBody("application/pdf".toMediaTypeOrNull())
    val multipartFile = MultipartBody.Part.createFormData(name, file.name, requestBody)

    return multipartFile
}

fun bitmapToBase64(bitmap: Bitmap): String{
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    val byteArray = outputStream.toByteArray()

    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun base64ToBitmap(string: String): Bitmap{
    val bytes = Base64.decode(string, Base64.DEFAULT)

    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}


fun uriToFile(context: Context, uri: Uri, documentName: String): File {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.cacheDir, "$documentName.pdf")

    inputStream?.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }

    Log.d("URI_TO_FILE", file.extension)

    return file
}

fun pdfPageToBitmap(file: File, pageNumber: Int): Bitmap {
    val resultBitmap: Bitmap

    val parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
    val renderer = PdfRenderer(parcelFileDescriptor)

    renderer.use { r ->
        val page = r.openPage(pageNumber)
        val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
        page.use {
            it.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        }
        resultBitmap = bitmap
    }

    return resultBitmap
}