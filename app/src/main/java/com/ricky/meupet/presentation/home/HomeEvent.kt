package com.ricky.meupet.presentation.home

sealed interface HomeEvent {
    data class OnChangeTheme(val isDark: Boolean) : HomeEvent
}