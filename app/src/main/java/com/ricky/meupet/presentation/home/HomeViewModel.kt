package com.ricky.meupet.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.meupet.common.Constants
import com.ricky.meupet.common.DataStoreUtil
import com.ricky.meupet.common.calculateAgeAndMonths
import com.ricky.meupet.common.convertToDate
import com.ricky.meupet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val petRepository: PetRepository,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        recuperaTema()
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            recuperaPet(petId)
        }
    }

    private fun recuperaPet(petId: String) {
        viewModelScope.launch {
            petRepository.getPetById(petId).let {
                it.idade = calculateAgeAndMonths(it.nascimento.convertToDate()!!)

                _state.value = _state.value.copy(
                    pet = it
                )

            }

        }
    }

    private fun recuperaTema() {
        viewModelScope.launch {
            dataStoreUtil.getTheme().collect { isDark ->
                _state.update {
                    it.copy(isDark = isDark)
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnChangeTheme -> {
                viewModelScope.launch {
                    dataStoreUtil.saveTheme(event.isDark)
                }
            }

            is HomeEvent.OnChangeTela -> {
                _state.update {
                    it.copy(
                        label = event.label
                    )
                }
            }
        }
    }
}