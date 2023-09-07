package com.ricky.meupet.presentation.config

import android.content.Context
import android.net.Uri
import com.ricky.meupet.domain.model.tipos.AnimalGenero
import com.ricky.meupet.domain.model.tipos.AnimalTipo

sealed interface ConfigEvents{
    object AddPet : ConfigEvents
    object ShowDataPicker : ConfigEvents
    data class OnChangeNome(val nome: String) : ConfigEvents
    data class OnChangeTipo(val tipo: AnimalTipo) : ConfigEvents
    data class OnChangeRaca(val raca: String) : ConfigEvents
    data class OnChangeGenero(val genero: AnimalGenero) : ConfigEvents
    data class OnChangePeso(val peso: String) : ConfigEvents
    data class OnChangeDate(val date: Long) : ConfigEvents
    data class SelectPhoto(val uri: Uri?, val context: Context) : ConfigEvents
}