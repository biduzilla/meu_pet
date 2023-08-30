package com.ricky.meupet.data.repository

import com.ricky.meupet.data.dao.MedicamentoDao
import com.ricky.meupet.domain.model.Aplicacao
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes
import com.ricky.meupet.domain.repository.MedicamentoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicamentoRepositoryImpl @Inject constructor(private val dao: MedicamentoDao) :
    MedicamentoRepository {
    override fun getAllMedicamentos(): Flow<List<Medicamento>> = dao.getAllMedicamentos()

    override suspend fun getMedicamentoById(medicamentoId: String): Medicamento =
        dao.getMedicamentoById(medicamentoId)

    override suspend fun insertMedicamento(medicamento: Medicamento) =
        dao.insertMedicamento(medicamento)

    override suspend fun updateMedicamento(medicamento: Medicamento) =
        dao.updateMedicamento(medicamento)

    override suspend fun deleteMedicamento(medicamento: Medicamento) =
        dao.deleteMedicamento(medicamento)

    override suspend fun deleteMedicamentoById(medicamentoId: String) =
        dao.deleteMedicamentoById(medicamentoId)

    override suspend fun getMedicamentoWithAplicacaoById(medicamentoId: String): MedicamentoWithAplicacoes {
        return dao.getMedicamentoWithAplicacaoById(medicamentoId = medicamentoId)
    }

    override suspend fun insertMedicamentoWithAplicacoes(
        medicamento: Medicamento,
        aplicacoes: List<Aplicacao>
    ) {
        dao.insertMedicamentoWithAplicacoes(medicamento, aplicacoes)
    }

    override suspend fun updateMedicamentoWithAplicacoes(
        medicamento: Medicamento,
        aplicacoes: List<Aplicacao>
    ) {
        dao.updateMedicamentoWithAplicacoes(medicamento, aplicacoes)
    }

    override fun getMedicamentosWithAplicacaoByPetId(petId: String): Flow<List<MedicamentoWithAplicacoes>> {
        return dao.getMedicamentosWithAplicacaoByPetId(petId)
    }
}