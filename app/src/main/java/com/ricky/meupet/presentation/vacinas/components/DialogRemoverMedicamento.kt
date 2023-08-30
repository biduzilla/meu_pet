package com.ricky.meupet.presentation.vacinas.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.meupet.R
import com.ricky.meupet.ui.theme.MeuPetTheme

@Composable
fun DialogRemoverMedicamento(
    modifier: Modifier = Modifier,
    onDimiss: () -> Unit,
    onRemoverMedicamento: () -> Unit
) {

    AlertDialog(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        text = {
            Text(
                text = stringResource(id = R.string.titulo_apagar_vacina),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        onDismissRequest = { onDimiss() },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onRemoverMedicamento() }) {
                    Text(text = stringResource(id = R.string.apagar))
                }
                Button(onClick = { onDimiss() }) {
                    Text(text = stringResource(id = R.string.cancelar))
                }
            }
        },
    )
}

@Preview
@Composable
private fun DialogAddProdutoPreview() {
    MeuPetTheme {
        DialogRemoverMedicamento(
            onRemoverMedicamento = {},
            onDimiss = {}
        )
    }
}