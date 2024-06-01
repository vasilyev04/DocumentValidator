package com.vasilyev.documentvalidator.presentation.screens.home

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
class HomeViewModel @Inject constructor(
    private val getRecentResultsUseCase: GetRecentResultsUseCase
): ViewModel(){

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            getRecentResultsUseCase().collect { list ->
                if(list.isNotEmpty()){
                    _homeState.update {
                        it.copy(
                            checkingResultList = list.subList(0, 2),
                            isLoading = false
                        )
                    }
                }else{
                    _homeState.update {
                        it.copy(
                            checkingResultList = list,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}