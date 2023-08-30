package com.ricky.meupet.domain

import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes

data class MedicamentosMesAno(
    var medicamentos: List<MedicamentoWithAplicacoes> = emptyList(),
    var aplicado: Boolean = false,
    var mesAno: String = "",
)
