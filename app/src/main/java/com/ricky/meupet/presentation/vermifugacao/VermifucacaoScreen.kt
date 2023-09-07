package com.ricky.meupet.presentation.vermifugacao

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ricky.meupet.R
import com.ricky.meupet.presentation.eventos.components.EventoCompose
import com.ricky.meupet.presentation.vacinas.VacinaEvent
import com.ricky.meupet.presentation.vacinas.VacinaState
import com.ricky.meupet.presentation.vacinas.components.CardRemedio
import com.ricky.meupet.presentation.vacinas.components.DialogForm

@Composable
fun VermifugacaoScreen(
    state: VacinaState,
    onEvent: (VacinaEvent) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onEvent(VacinaEvent.OnShowDialog) }) {
            Icon(imageVector = Icons.Default.Medication, contentDescription = null)
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
                onSave = {
                    onEvent(VacinaEvent.OnSaveVacina)
                },
                isProxVacina = { onEvent(VacinaEvent.IsSelectProxVacina(it)) }
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .padding(paddingValues),
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.prox_verm),
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.vacinasNaoAplicadas) { item ->
                EventoCompose(evento = item)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.vermifugacao_aplicada),
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.medicamentosMesAno) { item ->
                Text(
                    text = item.mesAno,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                item.medicamentos.forEach { medicamento ->
                    CardRemedio(
                        medicamento = medicamento,
                        onRemoverMedicamento = {
                            onEvent(VacinaEvent.OnDeleteVacina(medicamento.medicamento.id))
                        })
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
    }

}