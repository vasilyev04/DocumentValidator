package com.vasilyev.documentvalidator.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilyev.documentvalidator.domain.usecase.GetRecentResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecentResultsUseCase: GetRecentResultsUseCase
): ViewModel(){

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            getRecentResultsUseCase().collect { list ->
                if(list.isNotEmpty()){
                    _homeState.value = HomeState.CheckingResultListReceived(list.subList(0, 2))
                }else{
                    _homeState.value = HomeState.CheckingResultListReceived(list)
                }
            }
        }
    }
}