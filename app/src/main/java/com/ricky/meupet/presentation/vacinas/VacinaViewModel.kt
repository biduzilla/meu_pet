package com.ricky.meupet.presentation.vacinas

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class VacinaViewModel:ViewModel() {
    private val _state = MutableStateFlow(VacinaState())
    val state = _state.asStateFlow()

    fun onEvent(event: VacinaEvent){

    }
}