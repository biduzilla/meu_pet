package com.ricky.meupet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ricky.meupet.domain.model.enum.MedicamentoTipo
import java.util.UUID

@Entity
data class Medicamento(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var nome: String = "",
    var descricao: String = "",
    var data: String = "",
    var proximaAplicacao: String = "",
    var aplicado: Boolean = false,
    var tipo: MedicamentoTipo = MedicamentoTipo.VACINA,
    var petId: String =""
)
