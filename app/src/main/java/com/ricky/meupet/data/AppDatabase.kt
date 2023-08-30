package com.ricky.meupet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ricky.meupet.data.converters.Converters
import com.ricky.meupet.data.dao.AplicacaoDao
import com.ricky.meupet.data.dao.MedicamentoDao
import com.ricky.meupet.data.dao.PetDao
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.Pet

@Database(
    entities = [
        Pet::class,
        Medicamento::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {

    abstract fun petDao():PetDao
    abstract fun medicamentoDao():MedicamentoDao
    abstract fun aplicacaoDao():AplicacaoDao
}