package com.ricky.meupet.presentation.form

import androidx.lifecycle.ViewModel
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

            }

            is FormEvent.OnChangeGenero -> {

            }

            is FormEvent.OnChangeNascimento -> {

            }

            is FormEvent.OnChangeNome -> {
            }

            is FormEvent.OnChangePeso -> {

            }

            is FormEvent.OnChangeRaca -> {

            }

            is FormEvent.OnChangeTipo -> {

            }

            is FormEvent.OnChangepathPhoto -> {

            }

            FormEvent.ShowDataPicker -> {

            }
        }
    }
}