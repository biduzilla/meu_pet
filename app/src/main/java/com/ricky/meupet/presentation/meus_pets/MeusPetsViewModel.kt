package com.ricky.meupet.presentation.meus_pets

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.meupet.common.calculateAgeAndMonths
import com.ricky.meupet.common.convertToDate
import com.ricky.meupet.common.convertToStringPrecisa
import com.ricky.meupet.common.notificacao.NotificationService
import com.ricky.meupet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MeusPetsViewModel @Inject constructor(
    private val notificationService: NotificationService,
    private val repository: PetRepository
) : ViewModel() {
    private val _state = MutableStateFlow(MeusPetsState())
    val state = _state.asStateFlow()

    init {
        recuperaPets()
    }

    private fun recuperaPets() {
        viewModelScope.launch {
            repository.getAllPets().let { pets ->
                pets.collect { petsRecuperados ->
                    petsRecuperados.map {
                        it.idade = calculateAgeAndMonths(it.nascimento.convertToDate()!!)
                    }
                    _state.value = _state.value.copy(
                        pets = petsRecuperados,
                    )
                }
            }
        }
    }
}