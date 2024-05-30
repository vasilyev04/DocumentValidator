package com.vasilyev.documentvalidator.presentation.screens.document

interface DocumentIntent {
    data class OnSearchValueChanged(val query: String): DocumentIntent
}