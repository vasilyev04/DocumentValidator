package com.vasilyev.documentvalidator.common.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun createMultiPartFile(file: File, name: String): MultipartBody.Part{
    val requestBody = file.asRequestBody("application/pdf".toMediaTypeOrNull())
    val multipartFile = MultipartBody.Part.createFormData(name, file.name, requestBody)

    return multipartFile
}