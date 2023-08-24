package com.ricky.meupet.data.converters

import androidx.room.TypeConverter
import com.ricky.meupet.domain.model.enum.AnimalTipo
import com.ricky.meupet.domain.model.enum.MedicamentoTipo

class Converters {
    @TypeConverter
    fun fromTipoMedicamento(tipo: MedicamentoTipo): String {
        return tipo.name
    }

    @TypeConverter
    fun toTipoMedicamento(value: String): MedicamentoTipo {
        return enumValueOf(value)
    }

    @TypeConverter
    fun fromTipoAnimal(tipo: AnimalTipo): String {
        return tipo.name
    }

    @TypeConverter
    fun toTipoAnimal(value: String): AnimalTipo {
        return enumValueOf(value)
    }
}