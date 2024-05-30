package com.vasilyev.documentvalidator.presentation.screens.home

import com.vasilyev.documentvalidator.domain.models.CheckingResult

data class HomeState(
    val checkingResultList: List<CheckingResult> = emptyList(),
    val isLoading: Boolean = true
)