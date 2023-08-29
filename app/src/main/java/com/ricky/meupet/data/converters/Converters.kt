package com.ricky.meupet.data.converters

import androidx.room.TypeConverter
import com.ricky.meupet.domain.model.tipos.AnimalGenero
import com.ricky.meupet.domain.model.tipos.AnimalTipo
import com.ricky.meupet.domain.model.tipos.MedicamentoTipo
import java.math.BigDecimal

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
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }
}