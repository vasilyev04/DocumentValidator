package com.vasilyev.documentvalidator.domain.usecase

import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Resource
import com.vasilyev.documentvalidator.domain.repository.ValidateDocumentsRepository
import java.io.File
import javax.inject.Inject

class ValidateDocumentUseCase @Inject constructor(
    private val validateRepository: ValidateDocumentsRepository
) {
    suspend operator fun invoke(file: File): Resource<CheckingResult> {
        return validateRepository.validateDocument(file)
    }
}