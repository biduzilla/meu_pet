package com.ricky.meupet.presentation.form

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.meupet.common.calculateAgeAndMonths
import com.ricky.meupet.common.convertToString
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class FormViewModel @Inject constructor(private val repository: PetRepository) : ViewModel() {
    private val _state = MutableStateFlow(FormState())
    val state = _state.asStateFlow()

    private lateinit var context: Context

    fun onEvent(event: FormEvent) {
        when (event) {
            FormEvent.AddPet -> {
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
//                val pet = Pet(
//                    nome = _state.value.nome,
//                    idade = _state.value.idade,
//                    nascimento = _state.value.nascimento,
//                    tipo = _state.value.tipo,
//                    raca = _state.value.raca,
//                    genero = _state.value.genero,
//                    peso = BigDecimal(_state.value.peso)
//                )
//                Log.i("infoteste", "onEvent: $pet")

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
                    Log.i("infoteste", "onEvent: $pet")
                    viewModelScope.launch {
                        repository.insertPet(pet)
                        _state.value = _state.value.copy(
                            isOk = true
                        )
                    }
                } ?: run {
                    return
                }
            }

            is FormEvent.OnChangeDate -> {
                val calendar = Calendar.getInstance()
                calendar.time = Date(event.date)
                calendar.add(Calendar.DAY_OF_YEAR, 1)

                _state.value = _state.value.copy(
                    nascimento = calendar.time.convertToString(),
                    idade = calculateAgeAndMonths(Date(event.date)),
                    onErrorNascimento = false
                )
            }

            is FormEvent.OnChangeGenero -> {
                _state.value = _state.value.copy(
                    genero = event.genero
                )
            }

            is FormEvent.OnChangeNome -> {
                _state.value = _state.value.copy(
                    nome = event.nome,
                    onErrorNome = false
                )
            }

            is FormEvent.OnChangePeso -> {
                _state.value = _state.value.copy(
                    peso = event.peso,
                    onErrorPeso = false
                )

            }

            is FormEvent.OnChangeRaca -> {
                _state.value = _state.value.copy(
                    raca = event.raca,
                    onErrorRaca = false
                )

            }

            is FormEvent.OnChangeTipo -> {
                _state.value = _state.value.copy(
                    tipo = event.tipo
                )
            }

            FormEvent.ShowDataPicker -> {
                _state.value = _state.value.copy(
                    isShowDataPicker = !_state.value.isShowDataPicker
                )
            }

            is FormEvent.SelectPhoto -> {
                _state.value = _state.value.copy(
                    pathFoto = event.uri.toString(),
                    onErrorPhoto = false
                )
                context = event.context
            }
        }
    }

    private fun saveImageToInternalStorage(id: String, context: Context, uri: Uri): String? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = context.openFileOutput("$id.jpg", Context.MODE_PRIVATE)
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            return context.getFileStreamPath("$id.jpg")?.absolutePath
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                onErrorPhoto = true,
                pathFoto = ""
            )
        }
        return null
    }
}