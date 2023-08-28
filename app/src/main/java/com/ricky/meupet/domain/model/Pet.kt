package com.ricky.meupet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ricky.meupet.domain.model.enum.AnimalGenero
import com.ricky.meupet.domain.model.enum.AnimalTipo
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Pet(
    @PrimaryKey
    var id:String = UUID.randomUUID().toString(),
    var nome:String = "",
    var idade:String = "",
    var nascimento:String = "",
    var pathFoto:String = "",
    var tipo: AnimalTipo = AnimalTipo.CACHORRO,
    var raca:String ="",
    var genero:AnimalGenero = AnimalGenero.FEMEA,
    var peso:BigDecimal = BigDecimal(0.0),
)
