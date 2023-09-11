package com.ricky.meupet.presentation.medicamentos

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ricky.meupet.presentation.vacinas.VacinaEvent
import com.ricky.meupet.presentation.vacinas.VacinaState
import com.ricky.meupet.presentation.vacinas.components.CardRemedio
import com.ricky.meupet.presentation.vacinas.components.DialogForm

@Composable
fun MedicamentosScreen(
    state: VacinaState,
    onEvent: (VacinaEvent) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onEvent(VacinaEvent.OnShowDialog) }) {
            Icon(imageVector = Icons.Default.MedicalInformation, contentDescription = null)
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
            items(state.medicamentosMesAno) { item ->
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