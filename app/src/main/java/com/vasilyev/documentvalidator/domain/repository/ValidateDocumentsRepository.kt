package com.vasilyev.documentvalidator.domain.repository

import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Document
import java.io.File

interface ValidateDocumentsRepository {
    suspend fun validateDocument(
        file: File,
        documentType: Document,
        uploadDate: String
    ): Result<CheckingResult>
}