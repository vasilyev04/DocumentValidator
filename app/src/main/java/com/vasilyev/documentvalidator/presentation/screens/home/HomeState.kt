package com.vasilyev.documentvalidator.presentation.screens.home

import com.vasilyev.documentvalidator.domain.models.CheckingResult

sealed class HomeState {
    class CheckingResultListReceived(val list: List<CheckingResult>): HomeState()
    data object Loading: HomeState()
}