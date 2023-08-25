package com.ricky.meupet.data.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.Pet

data class PetsWithMedicamentos(
    @Embedded val pet: Pet,
    @Relation(
        parentColumn = "id",
        entityColumn = "petId"
    )
    var medicamentos: List<Medicamento> = emptyList()
)
