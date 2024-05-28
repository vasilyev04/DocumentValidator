package com.vasilyev.documentvalidator.presentation.screens.result

import androidx.lifecycle.ViewModel
import com.vasilyev.documentvalidator.domain.usecase.GetRecentResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val getRecentResultUseCase: GetRecentResultUseCase
): ViewModel()