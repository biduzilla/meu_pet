package com.ricky.meupet.presentation.vermifugacao

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class VermifucacaoViewModel : ViewModel() {
    private val _state = MutableStateFlow(VermifugacaoState())
    val state = _state.asStateFlow()

    fun onEvent(event: VermifugacaoEvent){

    }
}