package com.ricky.meupet.presentation.eventos

sealed interface EventosEvent{
    data class OnGetPetId(val petId: String):EventosEvent
}