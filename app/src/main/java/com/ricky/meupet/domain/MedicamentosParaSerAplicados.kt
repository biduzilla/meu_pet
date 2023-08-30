package com.ricky.meupet.domain

import com.ricky.meupet.domain.model.Medicamento

data class MedicamentosParaSerAplicados(
    var medicamento: Medicamento,
    var dataAplicacao: String = ""
)
