package com.ricky.meupet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Aplicacao(
    @PrimaryKey
    var id: String = "",
    var data: String = "",
    var proximaAplicacao: String = "",
    var aplicado: Boolean = false,
    var medicamentoId: String = "",
)
