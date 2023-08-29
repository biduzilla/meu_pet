package com.ricky.meupet.presentation.vermifugacao

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun VermifugacaoScreen(
    state: VermifugacaoState,
    onEvent: (VermifugacaoEvent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Vermif")
    }
}