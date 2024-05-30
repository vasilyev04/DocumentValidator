package com.vasilyev.documentvalidator.data.source.remote.retrofit

import com.vasilyev.documentvalidator.data.source.remote.models.CheckStatusDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("/gpt/?type=ID_CARD")
    suspend fun checkIDCard(
        @Part file: MultipartBody.Part
    ): Result<CheckStatusDto>

    @Multipart
    @POST("/gpt/?type=BIRT_DOCUMENT")
    suspend fun checkBirtDocument(
        @Part file: MultipartBody.Part
    ): Result<CheckStatusDto>

    @Multipart
    @POST("/gpt/?type=DRIVER_LICENSE")
    suspend fun checkDriverLicense(
        @Part file: MultipartBody.Part
    ): Result<CheckStatusDto>
}