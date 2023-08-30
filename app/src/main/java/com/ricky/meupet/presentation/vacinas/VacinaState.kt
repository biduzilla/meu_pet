package com.ricky.meupet.presentation.vacinas

import com.ricky.meupet.domain.MedicamentosMesAno
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes

data class VacinaState(
    val medicamentosMesAno: List<MedicamentosMesAno> = emptyList(),
    val vacinasNaoAplicadas:List<MedicamentoWithAplicacoes> = emptyList(),
    val isShowDialogForm: Boolean = false,
    val isShowDialogData: Boolean = false,
    val isProxVacina: Boolean = false,
    val nome: String = "",
    val descricao: String = "",
    val dataAplicacao: String = "",
    val dataProxAplicacao: String = "",
    val onErrorNome: Boolean = false,
    val onErrorDescricao: Boolean = false,
    val onErrorData: Boolean = false,
)
