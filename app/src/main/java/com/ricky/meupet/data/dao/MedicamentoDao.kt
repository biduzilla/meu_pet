package com.ricky.meupet.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface MedicamentoDao {
    @Query("SELECT * FROM MEDICAMENTO")
    fun getAllMedicamentos(): Flow<List<Medicamento>>

    @Query("SELECT * FROM MEDICAMENTO WHERE id = :medicamentoId")
    suspend fun getMedicamentoById(medicamentoId:String): Medicamento

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicamento(medicamento: Medicamento)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMedicamento(medicamento: Medicamento)

    @Delete
    suspend fun deleteMedicamento(medicamento: Medicamento)

    @Query("DELETE FROM MEDICAMENTO WHERE id = :medicamentoId")
    suspend fun deleteMedicamentoById(medicamentoId:String)
}