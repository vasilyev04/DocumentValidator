package com.vasilyev.documentvalidator.presentation.screens.home

import com.vasilyev.documentvalidator.domain.models.Document

sealed interface HomeIntent {
    data class DocumentSelected(val document: Document): HomeIntent
}