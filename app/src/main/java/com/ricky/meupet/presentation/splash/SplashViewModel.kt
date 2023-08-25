package com.ricky.meupet.presentation.splash

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import com.ricky.meupet.presentation.config.ConfigState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(): ViewModel() {
    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    init {
        tempoEspera()
    }

    private fun tempoEspera() {
        Handler(Looper.getMainLooper()).postDelayed({
            _state.value = _state.value.copy(
                isLoading = true
            )
        }, 3000)
    }
}