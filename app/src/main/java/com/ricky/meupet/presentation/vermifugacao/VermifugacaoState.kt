package com.ricky.meupet.presentation.vermifugacao

import com.ricky.meupet.domain.MedicamentosMesAno
import com.ricky.meupet.domain.MedicamentosParaSerAplicados

data class VermifugacaoState(
    val medicamentosMesAno: List<MedicamentosMesAno> = emptyList(),
    val vacinasNaoAplicadas:List<MedicamentosParaSerAplicados> = emptyList(),
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
