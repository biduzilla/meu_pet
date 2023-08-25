package com.ricky.meupet.presentation.meus_pets

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MeusPetsViewModel @Inject constructor():ViewModel() {
    private val _state = MutableStateFlow(MeusPetsState())
    val state = _state.asStateFlow()

    fun onEvent(event: MeusPetsEvent){

    }
}