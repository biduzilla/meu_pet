package com.ricky.meupet.presentation.vacinas.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ricky.meupet.R
import com.ricky.meupet.domain.model.tipos.MedicamentoTipo
import com.ricky.meupet.presentation.form.components.DateDialiog
import com.ricky.meupet.presentation.form.components.TextFieldCompose
import com.ricky.meupet.presentation.vacinas.VacinaState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogForm(
    modifier: Modifier = Modifier,
    state: VacinaState,
    onChangeNome: (String) -> Unit,
    onChangeDescricao: (String) -> Unit,
    onDimiss: () -> Unit,
    onDimissDataDialiog: () -> Unit,
    showDataDialog: () -> Unit,
    onDataAplicacao: (Long) -> Unit,
    onDataProxAplicacao: (Long) -> Unit,
    onSave: () -> Unit,
    isProxVacina: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val keyBoardManager = LocalFocusManager.current

    if (state.isShowDialogData) {
        DateDialiog(isPassado = !state.isProxVacina,
            onDimiss = onDimissDataDialiog,
            onChangeDate = { data ->
                if (state.isProxVacina) {
                    onDataProxAplicacao(data)
                } else {
                    onDataAplicacao(data)
                }
            })
    }
    Dialog(onDismissRequest = onDimiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(
                        id =
                        when (state.medicamentoTipo) {
                            MedicamentoTipo.MEDICAMENTO -> R.string.add_medicamento
                            MedicamentoTipo.VACINA -> R.string.add_vacina
                            MedicamentoTipo.VERMIFUGACAO -> R.string.add_vermifugacao
                        }
                    ),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                TextFieldCompose(
                    value = state.nome,
                    isError = state.onErrorNome,
                    label =
                    when (state.medicamentoTipo) {
                        MedicamentoTipo.MEDICAMENTO -> R.string.nome_medicamento
                        MedicamentoTipo.VACINA -> R.string.nome_vacina
                        MedicamentoTipo.VERMIFUGACAO -> R.string.nome_vermifugacao
                    },
                    onChange = { onChangeNome(it) }
                )
                TextFieldCompose(
                    value = state.descricao,
                    isError = state.onErrorDescricao,
                    label = when (state.medicamentoTipo) {
                        MedicamentoTipo.MEDICAMENTO -> R.string.descricao_medicamento
                        MedicamentoTipo.VACINA -> R.string.descricao_vacina
                        MedicamentoTipo.VERMIFUGACAO -> R.string.descricao_vermifugacao
                    },
                    onChange = { onChangeDescricao(it) }
                )
                if (state.medicamentoTipo != MedicamentoTipo.MEDICAMENTO) {
                    Text(
                        text = stringResource(id = R.string.data_aplicacao),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        onClick = {
                            showDataDialog()
                            isProxVacina(false)
                        },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(
                            text = state.dataAplicacao.ifBlank { stringResource(id = R.string.escolher_data) },
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.prox_aplicacao),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(10.dp),
                        onClick = {
                            showDataDialog()
                            isProxVacina(true)
                        },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(
                            text = state.dataProxAplicacao.ifBlank { stringResource(id = R.string.escolher_data) },
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                Button(
                    onClick = {
                        keyBoardManager.clearFocus()
                        if (state.onErrorData) {
                            Toast.makeText(
                                context,
                                "Escolha uma data",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            onSave()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.salva),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }
}