package com.ricky.meupet.presentation.eventos

import com.ricky.meupet.domain.MedicamentosParaSerAplicados

data class EventosState(
    val medicamentos: List<MedicamentosParaSerAplicados> = emptyList()
)
