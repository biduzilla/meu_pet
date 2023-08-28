package com.ricky.meupet.presentation.meus_pets

import android.content.Context

sealed interface MeusPetsEvent{
    data class OnSendContext(val context: Context):MeusPetsEvent
}