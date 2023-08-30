package com.ricky.meupet.domain.repository

import com.ricky.meupet.domain.model.Aplicacao
import kotlinx.coroutines.flow.Flow

interface AplicacaoRepository {
    fun getAllAplicacoesByMedicamentoId(medicamentoId: String): Flow<List<Aplicacao>>
    suspend fun getAplicacaoById(aplicacaoId: String): Aplicacao
    suspend fun insertAplicacao(aplicacao: Aplicacao)
    suspend fun updateAplicacao(aplicacao: Aplicacao)
    suspend fun deleteAplicacao(aplicacao: Aplicacao)
    suspend fun deleteAplicacaoById(aplicacaoId: String)
}