package com.ricky.meupet.domain.model

import androidx.room.Entity
import com.ricky.meupet.domain.model.enum.AnimalGenero
import com.ricky.meupet.domain.model.enum.AnimalTipo
import java.util.UUID

@Entity
data class Pet(
    var id:String = UUID.randomUUID().toString(),
    var nome:String = "",
    var idade:String = "",
    var nascimento:String = "",
    var pathFoto:String = "",
    var tipo: AnimalTipo = AnimalTipo.CAO,
    var raca:String ="",
    var genero:AnimalGenero = AnimalGenero.FEMEA,
    var peso:Float = 0f,
    var medicamentos:List<Medicamento> = emptyList()
)
