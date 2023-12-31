package com.ricky.meupet.presentation.config

import com.ricky.meupet.domain.model.tipos.AnimalGenero
import com.ricky.meupet.domain.model.tipos.AnimalTipo

data class ConfigState(
    var nome: String = "",
    var idade: String = "",
    var nascimento: String = "",
    var pathFoto: String = "",
    var tempPathFoto: String = "",
    var tipo: AnimalTipo = AnimalTipo.CACHORRO,
    var raca: String = "",
    var genero: AnimalGenero = AnimalGenero.FEMEA,
    var peso: String = "",
    var isShowDataPicker: Boolean = false,
    var isShowDialog: Boolean = false,
    var onErrorNome: Boolean = false,
    var onErrorNascimento: Boolean = false,
    var onErrorRaca: Boolean = false,
    var onErrorPeso: Boolean = false,
    var onErrorPhoto: Boolean = false,
    var isOk: Boolean = false
)
