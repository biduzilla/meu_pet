package com.ricky.meupet.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import com.ricky.meupet.domain.model.enum.MedicamentoTipo
import java.util.UUID

@Entity(
    foreignKeys = [ForeignKey(
        entity = Pet::class,
        parentColumns = ["id"],
        childColumns = ["petId"],
        onDelete = CASCADE
    )]
)
data class Medicamento(
    var id: String = UUID.randomUUID().toString(),
    var nome: String = "",
    var descricao: String = "",
    var data: String = "",
    var proximaAplicacao: String = "",
    var aplicado: Boolean = false,
    var tipo: MedicamentoTipo = MedicamentoTipo.VACINA,
    val petId: String
)
