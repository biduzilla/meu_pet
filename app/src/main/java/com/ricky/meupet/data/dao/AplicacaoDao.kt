package com.ricky.meupet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.meupet.domain.model.Aplicacao
import kotlinx.coroutines.flow.Flow

@Dao
interface AplicacaoDao {
    @Query("SELECT * FROM Aplicacao  WHERE medicamentoId = :medicamentoId")
    fun getAllAplicacoesByMedicamentoId(medicamentoId: String): Flow<List<Aplicacao>>

    @Query("SELECT * FROM Aplicacao WHERE id = :aplicacaoId")
    suspend fun getAplicacaoById(aplicacaoId: String): Aplicacao

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAplicacao(aplicacao: Aplicacao)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAplicacao(aplicacao: Aplicacao)

    @Delete
    suspend fun deleteAplicacao(aplicacao: Aplicacao)

    @Query("DELETE FROM Aplicacao WHERE id = :aplicacaoId")
    suspend fun deleteAplicacaoById(aplicacaoId: String)
}