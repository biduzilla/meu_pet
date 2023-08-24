package com.ricky.meupet.presentation.splash

import androidx.lifecycle.ViewModel
import com.ricky.meupet.presentation.config.ConfigState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class SplashViewModel:ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()
}