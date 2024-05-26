package com.vasilyev.documentvalidator.presentation.screens.document

import com.vasilyev.documentvalidator.domain.models.CheckingResult

sealed class DocumentsState {
    data class CheckingResultListReceived(val list: List<CheckingResult>): DocumentsState()
    data object Loading: DocumentsState()
}