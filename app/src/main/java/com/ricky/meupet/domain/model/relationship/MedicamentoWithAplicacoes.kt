package com.ricky.meupet.domain.model.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.ricky.meupet.domain.model.Aplicacao
import com.ricky.meupet.domain.model.Medicamento

data class MedicamentoWithAplicacoes(
    @Embedded val medicamento: Medicamento,
    @Relation(
        parentColumn = "id",
        entityColumn = "medicamentoId"
    )
    var aplicacoes: List<Aplicacao> = emptyList()
)
