package com.ricky.meupet.presentation.config

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.meupet.common.Constants
import com.ricky.meupet.common.calculateAgeAndMonths
import com.ricky.meupet.common.convertToString
import com.ricky.meupet.common.saveImageToInternalStorage
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ConfigViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PetRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ConfigState())
    val state = _state.asStateFlow()
    private lateinit var context: Context

    init {
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let {
            recuperaPet(it)
        }
    }

    private fun recuperaPet(petId: String) {
        viewModelScope.launch {
            val pet = repository.getPetById(petId)

            _state.update {
                it.copy(
                    nome = pet.nome,
                    idade = pet.idade,
                    nascimento = pet.idade,
                    pathFoto = pet.pathFoto,
                    tipo = pet.tipo,
                    raca = pet.raca,
                    genero = pet.genero,
                    peso = pet.peso.toString(),
                )
            }
        }
    }

    fun onEvent(event: ConfigEvents) {
        when (event) {
            ConfigEvents.AddPet -> {
                if (_state.value.nome.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorNome = true
                    )
                    return
                }
                if (_state.value.raca.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorRaca = true
                    )
                    return
                }
                if (_state.value.peso.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorPeso = true
                    )
                    return
                }
                if (_state.value.nascimento.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorNascimento = true
                    )
                    return
                }
                if (_state.value.pathFoto.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorPhoto = true
                    )
                    return
                }

                val id = UUID.randomUUID().toString()
                val pathFile = saveImageToInternalStorage(
                    id = id,
                    context = context,
                    uri = _state.value.pathFoto.toUri()
                )

                pathFile?.let {
                    val pet = Pet(
                        id = id,
                        nome = _state.value.nome,
                        idade = _state.value.idade,
                        nascimento = _state.value.nascimento,
                        tipo = _state.value.tipo,
                        raca = _state.value.raca,
                        pathFoto = it,
                        genero = _state.value.genero,
                        peso = BigDecimal(_state.value.peso)
                    )
                    viewModelScope.launch {
                        repository.insertPet(pet)
                    }
                } ?: run {
                    _state.value = _state.value.copy(
                        onErrorPhoto = true,
                        pathFoto = ""
                    )
                }
            }

            is ConfigEvents.OnChangeDate -> {
                val calendar = Calendar.getInstance()
                calendar.time = Date(event.date)
                calendar.add(Calendar.DAY_OF_YEAR, 1)

                _state.value = _state.value.copy(
                    nascimento = calendar.time.convertToString(),
                    idade = calculateAgeAndMonths(Date(event.date)),
                    onErrorNascimento = false
                )
            }

            is ConfigEvents.OnChangeGenero -> {
                _state.value = _state.value.copy(
                    genero = event.genero
                )
            }

            is ConfigEvents.OnChangeNome -> {
                _state.value = _state.value.copy(
                    nome = event.nome,
                    onErrorNome = false
                )
            }

            is ConfigEvents.OnChangePeso -> {
                _state.value = _state.value.copy(
                    peso = event.peso,
                    onErrorPeso = false
                )

            }

            is ConfigEvents.OnChangeRaca -> {
                _state.value = _state.value.copy(
                    raca = event.raca,
                    onErrorRaca = false
                )

            }

            is ConfigEvents.OnChangeTipo -> {
                _state.value = _state.value.copy(
                    tipo = event.tipo
                )
            }

            ConfigEvents.ShowDataPicker -> {
                _state.value = _state.value.copy(
                    isShowDataPicker = !_state.value.isShowDataPicker
                )
            }

            is ConfigEvents.SelectPhoto -> {
                _state.value = _state.value.copy(
                    pathFoto = event.uri.toString(),
                    onErrorPhoto = false
                )
                context = event.context
            }
        }
    }
}