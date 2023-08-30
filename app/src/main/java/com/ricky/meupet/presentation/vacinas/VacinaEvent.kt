package com.ricky.meupet.presentation.vacinas

sealed interface VacinaEvent {
    object OnShowDialog : VacinaEvent
    object OnShowDialogData : VacinaEvent
    object OnDimissDialogData : VacinaEvent
    object OnDimissDialog : VacinaEvent
    data class OnChangeNome(val nome:String):VacinaEvent
    data class OnChangeDescricao(val descricao:String):VacinaEvent
    data class OnChangeData(val data:Long):VacinaEvent
    data class OnChangeProxData(val proxData:Long):VacinaEvent
}