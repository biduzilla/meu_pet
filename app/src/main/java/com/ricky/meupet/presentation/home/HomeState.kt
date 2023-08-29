package com.ricky.meupet.presentation.home

import com.ricky.meupet.domain.model.Pet

data class HomeState(
    val pet:Pet = Pet(),
    val isDark:Boolean = false
)
