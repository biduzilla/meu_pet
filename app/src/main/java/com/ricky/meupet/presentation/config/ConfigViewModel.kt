package com.ricky.meupet.presentation.config

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor() :ViewModel() {

    private val _state = MutableStateFlow(ConfigState())
    val state = _state.asStateFlow()

    fun onEvent(event: ConfigEvents){

    }
}