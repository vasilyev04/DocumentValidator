package com.vasilyev.documentvalidator.presentation.screens.document

import com.vasilyev.documentvalidator.domain.models.CheckingResult

data class DocumentState(
    val documents: List<CheckingResult> = emptyList(),
    val isLoading: Boolean = true,
    val query: String = ""
)