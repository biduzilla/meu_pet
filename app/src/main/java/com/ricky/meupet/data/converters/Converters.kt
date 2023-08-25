package com.ricky.meupet.data.converters

import androidx.room.TypeConverter
import com.ricky.meupet.domain.model.enum.AnimalGenero
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

    @TypeConverter
    fun fromGeneroAnimal(tipo: AnimalGenero): String {
        return tipo.name
    }

    @TypeConverter
    fun toGeneroAnimal(value: String): AnimalGenero {
        return enumValueOf(value)
    }
    @TypeConverter
    fun fromFloat(value: Float): String {
        return value.toString()
    }

    @TypeConverter
    fun toFloat(value: String): Float {
        return value.toFloat()
    }
}