package com.ricky.meupet.domain.repository

import com.ricky.meupet.domain.model.Aplicacao
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes
import kotlinx.coroutines.flow.Flow

interface MedicamentoRepository {
    fun getAllMedicamentos(): Flow<List<Medicamento>>
    suspend fun getMedicamentoById(medicamentoId: String): Medicamento
    suspend fun insertMedicamento(medicamento: Medicamento)
    suspend fun updateMedicamento(medicamento: Medicamento)
    suspend fun deleteMedicamento(medicamento: Medicamento)
    suspend fun deleteMedicamentoById(medicamentoId: String)
    suspend fun getMedicamentoWithAplicacaoById(medicamentoId: String): MedicamentoWithAplicacoes
    suspend fun insertMedicamentoWithAplicacoes(
        medicamento: Medicamento,
        aplicacoes: List<Aplicacao>
    )

    suspend fun updateMedicamentoWithAplicacoes(
        medicamento: Medicamento,
        aplicacoes: List<Aplicacao>
    )

    fun getMedicamentosWithAplicacaoByPetId(petId: String): Flow<List<MedicamentoWithAplicacoes>>
    suspend fun deleteAplicacao(aplicacao: Aplicacao)

    fun getAllVacinasNaoAplicadas(petId: String): Flow<List<MedicamentoWithAplicacoes>>

}