package com.ricky.meupet.presentation.vacinas

import androidx.lifecycle.ViewModel
import com.ricky.meupet.common.convertToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class VacinaViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(VacinaState())
    val state = _state.asStateFlow()

    fun onEvent(event: VacinaEvent) {
        when (event) {
            is VacinaEvent.OnChangeData -> {
                val calendar = Calendar.getInstance()
                calendar.time = Date(event.data)
                calendar.add(Calendar.DAY_OF_YEAR, 1)
                _state.update {
                    it.copy(dataAplicacao = calendar.time.convertToString())
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
                    it.copy(dataProxAplicacao = calendar.time.convertToString())
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