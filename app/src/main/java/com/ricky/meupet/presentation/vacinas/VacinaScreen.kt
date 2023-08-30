package com.ricky.meupet.presentation.vacinas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ricky.meupet.presentation.vacinas.components.CardRemedio
import com.ricky.meupet.presentation.vacinas.components.DialogForm

@Composable
fun VacinaScreen(
    state: VacinaState,
    onEvent: (VacinaEvent) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onEvent(VacinaEvent.OnShowDialog) }) {
            Icon(imageVector = Icons.Default.Vaccines, contentDescription = null)
        }
    }) { paddingValues ->
        if (state.isShowDialogForm) {
            DialogForm(
                state = state,
                onChangeNome = { onEvent(VacinaEvent.OnChangeNome(it)) },
                onChangeDescricao = { onEvent(VacinaEvent.OnChangeDescricao(it)) },
                onDimiss = { onEvent(VacinaEvent.OnDimissDialog) },
                showDataDialog = { onEvent(VacinaEvent.OnShowDialogData) },
                onDataAplicacao = { onEvent(VacinaEvent.OnChangeData(it)) },
                onDataProxAplicacao = { onEvent(VacinaEvent.OnChangeProxData(it)) },
                onDimissDataDialiog = { onEvent(VacinaEvent.OnDimissDialogData) },
                onSave = { onEvent(VacinaEvent.OnSaveVacina) },
                isProxVacina = { onEvent(VacinaEvent.IsSelectProxVacina(it)) }
            )
        }
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(state.medicamentosMesAno) { item ->
                Text(
                    text = item.mesAno,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Divider()
                item.medicamentos.forEach { medicamento ->
                    CardRemedio(medicamento = medicamento)
                }
            }
        }
    }
}