package com.vasilyev.documentvalidator.presentation.screens.document

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilyev.documentvalidator.domain.usecase.GetRecentResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(
    private val getRecentResultsUseCase: GetRecentResultsUseCase
): ViewModel() {
    private val _documentsState = MutableStateFlow(DocumentState())
    val documentsState = _documentsState.asStateFlow()

    init {
        viewModelScope.launch {
            getRecentResultsUseCase().collect {list ->
                _documentsState.update {
                    it.copy(documents = list, isLoading = false)
                }
            }
        }
    }

    fun reduce(intent: DocumentIntent){
        when(intent){
            is DocumentIntent.OnSearchValueChanged -> {
                _documentsState.update {
                    it.copy(query = intent.query, isLoading = false)
                }
            }
        }
    }

}