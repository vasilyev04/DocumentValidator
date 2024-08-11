package com.vasilyev.documentvalidator.presentation.screens.checking

import com.vasilyev.documentvalidator.domain.models.CheckingResult

data class CheckingState(
    val isLoading: Boolean = false,
    var checkingResultId: Int = CheckingResult.UNDEFINED_ID,
    val error: Throwable? = null
)