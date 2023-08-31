package com.ricky.meupet.presentation.eventos

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.meupet.common.Constants
import com.ricky.meupet.common.convertToDate
import com.ricky.meupet.common.convertToString
import com.ricky.meupet.common.isVacinaNaoAplicada
import com.ricky.meupet.common.medicamentoToMedicamentoEventos
import com.ricky.meupet.domain.MedicamentosParaSerAplicados
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes
import com.ricky.meupet.domain.repository.MedicamentoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventosViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MedicamentoRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(EventosState())
    val state = _state.asStateFlow()

//    init {
//        Log.i("infoteste", "init")
//        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petIdRecuperado ->
//            Log.i("infoteste", "petId: $petIdRecuperado")
//            recuperaMedicamentosFututos(petIdRecuperado)
//        }
//    }

    private fun recuperaMedicamentosFututos(petId: String) {
        viewModelScope.launch {
            repository.getMedicamentosWithAplicacaoByPetId(petId).collect { list ->
                _state.update {
                    it.copy(
                        medicamentos = medicamentoToMedicamentoEventos(list)
                    )
                }
            }
        }
    }




    fun onEvent(event: EventosEvent) {
        when (event) {
            is EventosEvent.OnGetPetId -> {
                Log.i("infoteste", "petId: ${event.petId}")
                try {
                    recuperaMedicamentosFututos(event.petId)
                } catch (e: Exception) {
                    Log.i("infoteste", "onEvent: ${e.stackTrace}", )
                    Log.i("infoteste", "onEvent: ${e.message}", )
                }

            }
        }
    }
}