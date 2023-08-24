package com.ricky.meupet.presentation.eventos

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class EventosViewModel:ViewModel() {
    private val _state = MutableStateFlow(EventosState())
    val state = _state.asStateFlow()

    fun onEvent(event: EventosEvent){

    }
}