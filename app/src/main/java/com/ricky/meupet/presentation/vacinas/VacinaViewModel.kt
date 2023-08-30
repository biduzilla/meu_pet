package com.ricky.meupet.presentation.vacinas

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.meupet.common.Constants
import com.ricky.meupet.common.convertToString
import com.ricky.meupet.domain.model.Aplicacao
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes
import com.ricky.meupet.domain.model.tipos.MedicamentoTipo
import com.ricky.meupet.domain.repository.MedicamentoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class VacinaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: MedicamentoRepository
) : ViewModel() {
    private val _state = MutableStateFlow(VacinaState())
    val state = _state.asStateFlow()
    lateinit var petId: String

    init {
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petIdRecuperado ->
            petId = petIdRecuperado
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
                        descricao = event.descricao.trim(),
                        onErrorDescricao = false
                    )
                }
            }

            is VacinaEvent.OnChangeNome -> {
                _state.update {
                    it.copy(
                        nome = event.nome.trim(),
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

            VacinaEvent.OnSaveVacina -> {
                if (_state.value.nome.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorNome = true
                        )
                    }
                    return
                }
                if (_state.value.descricao.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorDescricao = true
                        )
                    }
                    return
                }
                if (_state.value.dataAplicacao.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorData = true
                        )
                    }
                    return
                }
                if (_state.value.dataProxAplicacao.isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorData = true
                        )
                    }
                    return
                }

                val medicamento = Medicamento(
                    nome = _state.value.nome,
                    descricao = _state.value.descricao,
                    tipo = MedicamentoTipo.VACINA,
                    petId = petId
                )

                val aplicacoes: List<Aplicacao> = listOf(
                    Aplicacao(
                        id = UUID.randomUUID().toString(),
                        data = _state.value.dataAplicacao,
                        proximaAplicacao = _state.value.dataProxAplicacao,
                        aplicado = true
                    ), Aplicacao(
                        id = UUID.randomUUID().toString(),
                        data = _state.value.dataProxAplicacao,
                        aplicado = false
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
        }
    }
}