package com.vasilyev.documentvalidator.domain.repository

import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Resource
import java.io.File

interface ValidateDocumentsRepository {
    suspend fun validateDocument(file: File): Result<CheckingResult>
}