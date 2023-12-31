package com.ricky.meupet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.ricky.meupet.domain.model.Aplicacao
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicamentoDao {
    @Query("SELECT * FROM MEDICAMENTO")
    fun getAllMedicamentos(): Flow<List<Medicamento>>

    @Query("SELECT * FROM MEDICAMENTO WHERE id = :medicamentoId")
    suspend fun getMedicamentoById(medicamentoId: String): Medicamento

    @Transaction
    @Query("SELECT * FROM MEDICAMENTO WHERE id = :medicamentoId")
    suspend fun getMedicamentoWithAplicacaoById(medicamentoId: String): MedicamentoWithAplicacoes

    @Transaction
    @Query("SELECT * FROM MEDICAMENTO WHERE petId = :petId")
    fun getMedicamentosWithAplicacaoByPetId(petId: String): Flow<List<MedicamentoWithAplicacoes>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicamentoWithAplicacoes(
        medicamento: Medicamento,
        aplicacoes: List<Aplicacao>
    ) {
        insertMedicamento(medicamento)
        aplicacoes.forEach {
            it.medicamentoId = medicamento.id
        }
        insertAplicacaos(aplicacoes)
    }

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMedicamentoWithAplicacoes(
        medicamento: Medicamento,
        aplicacoes: List<Aplicacao>
    ) {
        updateMedicamento(medicamento)
        aplicacoes.forEach {
            it.medicamentoId = medicamento.id
        }
        updateAplicacaos(aplicacoes)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicamento(medicamento: Medicamento)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMedicamento(medicamento: Medicamento)

    @Delete
    suspend fun deleteMedicamento(medicamento: Medicamento)

    @Query("DELETE FROM MEDICAMENTO WHERE id = :medicamentoId")
    suspend fun deleteMedicamentoById(medicamentoId: String) {
        val medicamentoWithAplicacoes = getMedicamentoWithAplicacaoById(medicamentoId)

        medicamentoWithAplicacoes.aplicacoes.forEach {
            deleteAplicacao(it)
        }
        deleteMedicamento(medicamentoWithAplicacoes.medicamento)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAplicacaos(aplicacoes: List<Aplicacao>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAplicacaos(aplicacoes: List<Aplicacao>)

    @Delete
    suspend fun deleteAplicacao(aplicacao: Aplicacao)

}