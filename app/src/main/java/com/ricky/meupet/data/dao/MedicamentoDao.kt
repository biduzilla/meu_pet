package com.ricky.meupet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicamentoDao {
    @Query("SELECT * FROM MEDICAMENTO")
    fun getAllMedicamentos(): Flow<List<Medicamento>>

    @Query("SELECT * FROM MEDICAMENTO WHERE id = :medicamentoId")
    suspend fun getMedicamentoById(medicamentoId:String): Medicamento

    @Transaction
    @Query("SELECT * FROM MEDICAMENTO WHERE id = :medicamentoId")
    suspend fun getMedicamentoWithAplicacaoById(medicamentoId:String): MedicamentoWithAplicacoes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicamento(medicamento: Medicamento)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMedicamento(medicamento: Medicamento)

    @Delete
    suspend fun deleteMedicamento(medicamento: Medicamento)

    @Query("DELETE FROM MEDICAMENTO WHERE id = :medicamentoId")
    suspend fun deleteMedicamentoById(medicamentoId:String)
}