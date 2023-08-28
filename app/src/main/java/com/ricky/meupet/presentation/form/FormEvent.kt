package com.ricky.meupet.presentation.form

import com.ricky.meupet.domain.model.enum.AnimalGenero
import com.ricky.meupet.domain.model.enum.AnimalTipo

sealed interface FormEvent {
    object AddImage : FormEvent
    object AddPet : FormEvent
    object ShowDataPicker : FormEvent
    data class OnChangeNome(val nome: String) : FormEvent
    data class OnChangepathPhoto(val nome: String) : FormEvent
    data class OnChangeTipo(val tipo: AnimalTipo) : FormEvent
    data class OnChangeRaca(val raca: String) : FormEvent
    data class OnChangeGenero(val genero: AnimalGenero) : FormEvent
    data class OnChangePeso(val peso: String) : FormEvent
    data class OnChangeDate(val date: Long) : FormEvent
}