package com.vasilyev.documentvalidator.presentation.screens.checking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilyev.documentvalidator.domain.models.CheckingResult
import com.vasilyev.documentvalidator.domain.models.Document
import com.vasilyev.documentvalidator.domain.usecase.SaveCheckingResultUseCase
import com.vasilyev.documentvalidator.domain.usecase.ValidateDocumentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CheckingViewModel @Inject constructor(
    private val validateDocumentUseCase: ValidateDocumentUseCase,
    private val saveCheckingResultUseCase: SaveCheckingResultUseCase
): ViewModel() {
    private val _checkingState = MutableStateFlow(CheckingState())
    val checkingState = _checkingState.asStateFlow()

    fun reduce(intent: CheckingIntent){
        when(intent){
            is CheckingIntent.ValidateDocument -> {
                validateDocument(
                    file = intent.document,
                    documentType = intent.documentType,
                )
            }
        }
    }

    private fun validateDocument(
        file: File,
        documentType: Document,
    ){
        _checkingState.update{
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            val requestResult = validateDocumentUseCase(
                file = file,
                documentType = documentType,
                uploadDate = System.currentTimeMillis().toString()
            )

            requestResult.onSuccess { checkingResult ->
                saveCheckingResult(checkingResult)
            }.onFailure { error ->
                showError(error)
            }
        }
    }

    private fun showError(error: Throwable){
        _checkingState.update{
            it.copy(
                isLoading = false,
                error = error
            )
        }
    }

    private fun saveCheckingResult(checkingResult: CheckingResult){
        viewModelScope.launch(Dispatchers.IO) {
            val checkingResultId = saveCheckingResultUseCase(checkingResult)

            _checkingState.update{
                it.copy(
                    isLoading = false,
                    checkingResultId = checkingResultId
                )
            }
        }
    }
}