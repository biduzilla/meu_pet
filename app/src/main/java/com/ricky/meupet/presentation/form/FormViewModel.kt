package com.ricky.meupet.presentation.form

import androidx.lifecycle.ViewModel
import com.ricky.meupet.common.calculateAgeAndMonths
import com.ricky.meupet.common.convertToString
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(private val repository: PetRepository) : ViewModel() {
    private val _state = MutableStateFlow(FormState())
    val state = _state.asStateFlow()

    fun onEvent(event: FormEvent) {
        when (event) {
            FormEvent.AddImage -> {

            }

            FormEvent.AddPet -> {

            }

            is FormEvent.OnChangeDate -> {
                val (anos, meses) = calculateAgeAndMonths(Date(event.date))
                _state.value = _state.value.copy(
                    nascimento = Date(event.date).convertToString(),
                    idade = "$meses meses e $anos anos"
                )

            }

            is FormEvent.OnChangeGenero -> {
                _state.value = _state.value.copy(
                    genero = event.genero
                )
            }

            is FormEvent.OnChangeNome -> {
                _state.value = _state.value.copy(
                    nome = event.nome
                )
            }

            is FormEvent.OnChangePeso -> {
                _state.value = _state.value.copy(
                    peso = event.peso
                )

            }

            is FormEvent.OnChangeRaca -> {
                _state.value = _state.value.copy(
                    raca = event.raca
                )

            }

            is FormEvent.OnChangeTipo -> {
                _state.value = _state.value.copy(
                    tipo = event.tipo
                )

            }

            is FormEvent.OnChangepathPhoto -> {

            }

            FormEvent.ShowDataPicker -> {
                _state.value = _state.value.copy(
                    isShowDataPicker = !_state.value.isShowDataPicker
                )
            }
        }
    }
}