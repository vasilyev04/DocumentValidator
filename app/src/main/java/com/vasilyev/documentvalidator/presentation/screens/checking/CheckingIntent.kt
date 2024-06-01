package com.vasilyev.documentvalidator.presentation.screens.checking

import com.vasilyev.documentvalidator.domain.models.Document
import java.io.File

interface CheckingIntent {
    data class ValidateDocument(
        val document: File,
        val documentType: Document,
        val uploadDate: String
    ): CheckingIntent
}