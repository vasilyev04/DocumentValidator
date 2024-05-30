package com.vasilyev.documentvalidator.data.repository

import com.vasilyev.documentvalidator.common.mappers.toCheckingResultDbo
import com.vasilyev.documentvalidator.common.mappers.toCheckingResultModel
import com.vasilyev.documentvalidator.common.utils.createMultiPartFile
import com.vasilyev.documentvalidator.data.source.remote.models.CheckStatusDto
import com.vasilyev.documentvalidator.data.source.remote.retrofit.ApiService
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Document
import com.vasilyev.documentvalidator.domain.repository.RecentResultsRepository
import com.vasilyev.documentvalidator.domain.repository.ValidateDocumentsRepository
import java.io.File
import javax.inject.Inject

class ValidateDocumentsRepoImpl @Inject constructor(
    private val apiService: ApiService,
    private val recentResultRepo: RecentResultsRepository
): ValidateDocumentsRepository {

    override suspend fun validateDocument(
        file: File,
        documentType: Document,
        uploadDate: String,
    ): Result<CheckingResult> {
        val multiPart = createMultiPartFile(file, "file")

        val result = apiService.checkIDCard(multiPart)

        return if(result.isSuccess){
            val checkStatusDto = result.getOrNull()
                ?: return Result.failure(RuntimeException("response body is null"))

            val id = saveResentCheck(checkStatusDto, documentType, uploadDate)

            Result.success(recentResultRepo.getRecentResult(id))
        }else{
            Result.failure(result.exceptionOrNull()
                    ?: return Result.failure(RuntimeException("exception is null"))
            )
        }
    }

    private fun saveResentCheck(
        checkStatusDto: CheckStatusDto,
        documentType: Document,
        uploadDate: String
    ): Int {
        val checkingResultDbo = checkStatusDto.toCheckingResultDbo(
            documentType = documentType,
            documentPreview = "",
            uploadDate = uploadDate
        )

        return recentResultRepo.addRecentResult(checkingResultDbo.toCheckingResultModel())
    }
}