package com.ricky.meupet.presentation.vermifugacao

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VermifucacaoViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(VermifugacaoState())
    val state = _state.asStateFlow()

    fun onEvent(event: VermifugacaoEvent){

    }
}