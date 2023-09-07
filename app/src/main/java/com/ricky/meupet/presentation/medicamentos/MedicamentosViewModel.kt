package com.ricky.meupet.presentation.medicamentos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.meupet.common.Constants
import com.ricky.meupet.common.convertToString
import com.ricky.meupet.common.formatarListaMesAno
import com.ricky.meupet.domain.MedicamentosMesAno
import com.ricky.meupet.domain.model.Aplicacao
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes
import com.ricky.meupet.domain.model.tipos.MedicamentoTipo
import com.ricky.meupet.domain.repository.MedicamentoRepository
import com.ricky.meupet.domain.repository.PetRepository
import com.ricky.meupet.presentation.vacinas.VacinaEvent
import com.ricky.meupet.presentation.vacinas.VacinaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MedicamentosViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MedicamentoRepository,
    private val petRepository: PetRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(VacinaState())
    val state = _state.asStateFlow()
    lateinit var petId: String
    var pet = Pet()

    init {
        _state.update {
            it.copy(
                medicamentoTipo = MedicamentoTipo.MEDICAMENTO
            )
        }
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petIdRecuperado ->
            petId = petIdRecuperado
            recuperaMedicamentos(petId)

            viewModelScope.launch {
                pet = petRepository.getPetById(petId = petIdRecuperado)
            }
        }
    }

    private fun recuperaMedicamentos(petId: String) {
        viewModelScope.launch {
            repository.getMedicamentosWithAplicacaoByPetId(petId)
                .map { allItens ->
                    allItens.filter {
                        it.medicamento.tipo == MedicamentoTipo.MEDICAMENTO
                    }
                }
                .collect { itens ->
                    val medicamentosMesAnoList = mutableListOf<MedicamentosMesAno>()

                    val vacinasPorMesAno = itens
                        .flatMap { it.aplicacoes }
                        .groupBy { aplicacao ->
                            aplicacao.data.substring(3)
                        }

                    val medicamentosAlreadyAdded = mutableSetOf<MedicamentoWithAplicacoes>()

                    for ((mesAno, vacinas) in vacinasPorMesAno) {
                        val medicamentoWithAplicacoesList =
                            mutableListOf<MedicamentoWithAplicacoes>()

                        for (aplicacao in vacinas) {
                            val medicamento = itens.find { it.aplicacoes.contains(aplicacao) }
                            medicamento?.let {
                                if (!medicamentosAlreadyAdded.contains(it)) {
                                    medicamentoWithAplicacoesList.add(it)
                                    medicamentosAlreadyAdded.add(it)
                                }
                            }
                        }

                        if (medicamentoWithAplicacoesList.isNotEmpty()) {
                            medicamentosMesAnoList.add(
                                MedicamentosMesAno(
                                    medicamentos = medicamentoWithAplicacoesList,
                                    mesAno = mesAno
                                )
                            )
                        }
                    }

                    _state.update {
                        it.copy(
                            medicamentosMesAno = formatarListaMesAno(medicamentosMesAnoList)
                        )
                    }
                }
        }
    }

    fun onEvent(event: VacinaEvent) {
        when (event) {
            is VacinaEvent.OnChangeData -> {
                val calendar = Calendar.getInstance()
                calendar.time = Date(event.data)
                calendar.add(Calendar.DAY_OF_YEAR, 1)
                _state.update {
                    it.copy(
                        dataAplicacao = calendar.time.convertToString(),
                        onErrorData = false
                    )
                }
            }

            is VacinaEvent.OnChangeDescricao -> {
                _state.update {
                    it.copy(
                        descricao = event.descricao,
                        onErrorDescricao = false
                    )
                }
            }

            is VacinaEvent.OnChangeNome -> {
                _state.update {
                    it.copy(
                        nome = event.nome,
                        onErrorNome = false
                    )
                }
            }

            is VacinaEvent.OnChangeProxData -> {
                val calendar = Calendar.getInstance()
                calendar.time = Date(event.proxData)
                calendar.add(Calendar.DAY_OF_YEAR, 1)
                _state.update {
                    it.copy(
                        dataProxAplicacao = calendar.time.convertToString(),
                        onErrorData = false
                    )
                }
            }

            VacinaEvent.OnDimissDialog -> {
                _state.update {
                    it.copy(
                        isShowDialogForm = false,
                    )
                }
            }

            VacinaEvent.OnDimissDialogData -> {
                _state.update {
                    it.copy(
                        isShowDialogData = false,
                    )
                }
            }

            VacinaEvent.OnShowDialog -> {
                _state.update {
                    it.copy(
                        isShowDialogForm = true,
                    )
                }
            }

            VacinaEvent.OnShowDialogData -> {
                _state.update {
                    it.copy(
                        isShowDialogData = true,
                    )
                }
            }

            is VacinaEvent.OnSaveVacina -> {
                if (_state.value.nome.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorNome = true
                        )
                    }
                    return
                }

                if (_state.value.descricao.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorDescricao = true
                        )
                    }
                    return
                }

                val medicamento = Medicamento(
                    nome = _state.value.nome,
                    descricao = _state.value.descricao,
                    tipo = MedicamentoTipo.MEDICAMENTO,
                    petId = petId
                )

                val aplicacoes: List<Aplicacao> = listOf(
                    Aplicacao(
                        id = UUID.randomUUID().toString(),
                        data = "01/01/2000",
                        proximaAplicacao = "01/01/2000",
                        aplicado = true
                    )
                )

                viewModelScope.launch {
                    repository.insertMedicamentoWithAplicacoes(
                        medicamento = medicamento,
                        aplicacoes = aplicacoes
                    )

                    _state.value = VacinaState()
                }
            }

            is VacinaEvent.IsSelectProxVacina -> {
                _state.update {
                    it.copy(
                        isProxVacina = event.isProximaVacina
                    )
                }
            }

            is VacinaEvent.OnDeleteVacina -> {
                viewModelScope.launch {
                    repository.deleteMedicamentoById(event.vacinaId)
                }
            }
        }
    }
}