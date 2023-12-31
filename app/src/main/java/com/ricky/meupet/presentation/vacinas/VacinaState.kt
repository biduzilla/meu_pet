package com.ricky.meupet.presentation.vacinas

import com.ricky.meupet.domain.MedicamentosMesAno
import com.ricky.meupet.domain.MedicamentosParaSerAplicados
import com.ricky.meupet.domain.model.tipos.MedicamentoTipo

data class VacinaState(
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
    val medicamentoTipo:MedicamentoTipo =MedicamentoTipo.VACINA,
)
