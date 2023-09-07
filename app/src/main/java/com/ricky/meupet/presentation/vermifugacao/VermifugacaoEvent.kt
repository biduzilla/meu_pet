package com.ricky.meupet.presentation.vermifugacao

import com.ricky.meupet.presentation.vacinas.VacinaEvent

sealed interface VermifugacaoEvent{
    object OnShowDialog : VermifugacaoEvent
    object OnShowDialogData : VermifugacaoEvent
    object OnDimissDialogData : VermifugacaoEvent
    object OnDimissDialog : VermifugacaoEvent
    object OnSaveVacina : VermifugacaoEvent
    data class OnDeleteVacina(val vacinaId: String) : VermifugacaoEvent
    data class IsSelectProxVacina(val isProximaVacina: Boolean) : VermifugacaoEvent
    data class OnChangeNome(val nome: String) : VermifugacaoEvent
    data class OnChangeDescricao(val descricao: String) : VermifugacaoEvent
    data class OnChangeData(val data: Long) : VermifugacaoEvent
    data class OnChangeProxData(val proxData: Long) : VermifugacaoEvent
}