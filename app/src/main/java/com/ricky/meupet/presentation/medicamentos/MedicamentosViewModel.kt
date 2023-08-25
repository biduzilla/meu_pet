package com.ricky.meupet.presentation.medicamentos

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MedicamentosViewModel @Inject constructor():ViewModel() {
    private val _state = MutableStateFlow(MedicamentosState())
    val state = _state.asStateFlow()

    fun onEvent(event: MedicamentosEvent){

    }
}