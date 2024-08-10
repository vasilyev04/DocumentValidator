package com.vasilyev.documentvalidator.presentation.screens.home

import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Document

data class HomeState(
    val checkingResultList: List<CheckingResult> = emptyList(),
    val isLoading: Boolean = true,
    val selectedDocument: Document = Document.Undefined
)