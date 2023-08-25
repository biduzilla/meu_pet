package com.ricky.meupet.presentation.meus_pets

import com.ricky.meupet.domain.model.enum.AnimalGenero
import com.ricky.meupet.domain.model.enum.AnimalTipo

sealed interface MeusPetsEvent {
    object AddPet : MeusPetsEvent
    data class OnChangeNome(val nome: String) : MeusPetsEvent
    data class OnChangeIdade(val idade: String) : MeusPetsEvent
    data class OnChangeNascimento(val nascimento: String) : MeusPetsEvent
    data class OnChangeAnimalTipo(val tipo: AnimalTipo) : MeusPetsEvent
    data class OnChangeAnimalGenero(val tipo: AnimalGenero) : MeusPetsEvent
    data class OnChangeAnimalPeso(val peso: Float) : MeusPetsEvent
}