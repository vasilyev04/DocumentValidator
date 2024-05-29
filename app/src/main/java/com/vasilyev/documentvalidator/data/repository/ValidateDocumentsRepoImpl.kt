package com.vasilyev.documentvalidator.data.repository

import com.vasilyev.documentvalidator.common.mappers.toCheckingResultDbo
import com.vasilyev.documentvalidator.data.source.local.entity.CheckingResultDbo
import com.vasilyev.documentvalidator.data.source.remote.models.CheckStatusDto
import com.vasilyev.documentvalidator.data.source.remote.retrofit.ApiService
import com.vasilyev.documentvalidator.domain.models.CheckStatus
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Resource
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import com.vasilyev.documentvalidator.domain.repository.ValidateDocumentsRepository
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class ValidateDocumentsRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val repoImpl: RecentResultsRepository
): ValidateDocumentsRepository {
    override suspend fun validateDocument(file: File): Result<CheckingResult> {
        val requestBody = file.asRequestBody("application/pdf".toMediaTypeOrNull())
        val multipartFile = MultipartBody.Part.createFormData("file", file.name, requestBody)

        val result = apiService.checkIDCard(multipartFile)

        return if(result.isSuccess){
            val checkStatusDto = result.getOrNull() ?: throw RuntimeException("response body is null")

            Result.success(checkStatusDto.toCheckingResultDbo())
        }else{
            Result.failure(
                result.exceptionOrNull() ?: throw RuntimeException("exception is null")
            )
        }
    }

    private fun saveResentCheck(
        checkStatusDto: CheckStatusDto,
        documentName: String,
        documentPreview: String,
        uploadDate: String
    ): CheckingResult {
        repoImpl.insertRecentResult(checkStatusDto.toCheckingResultDbo())
    }
}