package com.ricky.meupet.presentation.medicamentos

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ricky.meupet.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MedicamentosViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(MedicamentosState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petIdRecuperado ->
            _state.update {
                it.copy(
                    it = petIdRecuperado
                )
            }
        }
    }

    fun onEvent(event: MedicamentosEvent) {

    }
}