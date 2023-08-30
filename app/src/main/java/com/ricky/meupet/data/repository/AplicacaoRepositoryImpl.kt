package com.ricky.meupet.data.repository

import com.ricky.meupet.data.AppDatabase
import com.ricky.meupet.data.dao.AplicacaoDao
import com.ricky.meupet.data.dao.PetDao
import com.ricky.meupet.domain.model.Aplicacao
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.repository.AplicacaoRepository
import com.ricky.meupet.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AplicacaoRepositoryImpl @Inject constructor(private val dao: AplicacaoDao) :
    AplicacaoRepository {
    override fun getAllAplicacoesByMedicamentoId(medicamentoId: String): Flow<List<Aplicacao>> {
        return dao.getAllAplicacoesByMedicamentoId(medicamentoId = medicamentoId)
    }

    override suspend fun getAplicacaoById(aplicacaoId: String): Aplicacao {
        return getAplicacaoById(aplicacaoId = aplicacaoId)
    }

    override suspend fun insertAplicacao(aplicacao: Aplicacao) {
        dao.insertAplicacao(aplicacao = aplicacao)
    }

    override suspend fun updateAplicacao(aplicacao: Aplicacao) {
        dao.updateAplicacao(aplicacao = aplicacao)
    }

    override suspend fun deleteAplicacao(aplicacao: Aplicacao) {
        dao.deleteAplicacao(aplicacao = aplicacao)
    }

    override suspend fun deleteAplicacaoById(aplicacaoId: String) {
        dao.deleteAplicacaoById(aplicacaoId = aplicacaoId)
    }

}