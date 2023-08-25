package com.ricky.meupet.presentation.form

import com.ricky.meupet.domain.model.enum.AnimalGenero
import com.ricky.meupet.domain.model.enum.AnimalTipo

data class FormState(
    var nome: String = "",
    var idade: String = "",
    var nascimento: String = "",
    var pathFoto: String = "",
    var tipo: AnimalTipo = AnimalTipo.CAO,
    var raca: String = "",
    var genero: AnimalGenero = AnimalGenero.FEMEA,
    var peso: Float = 0f,
    var isShowDataPicker: Boolean = false,
    var onErrorNome: Boolean = false,
    var onErrorNascimento: Boolean = false,
    var onErrorRaca: Boolean = false,
    var onErrorPeso: Boolean = false,
    var onErrorPhoto: Boolean = false,
)
