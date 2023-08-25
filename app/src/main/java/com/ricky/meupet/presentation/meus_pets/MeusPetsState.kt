package com.ricky.meupet.presentation.meus_pets

import com.ricky.meupet.domain.model.Pet

data class MeusPetsState(
    val pets: List<Pet> = emptyList()
)
