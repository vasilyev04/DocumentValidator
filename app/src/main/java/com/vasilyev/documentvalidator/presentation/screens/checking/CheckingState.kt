package com.vasilyev.documentvalidator.presentation.screens.checking

import com.vasilyev.documentvalidator.domain.models.CheckingResult

data class CheckingState(
    val isLoading: Boolean = false,
    val checkingResult: CheckingResult? = null,
    val error: Throwable? = null
) {
}