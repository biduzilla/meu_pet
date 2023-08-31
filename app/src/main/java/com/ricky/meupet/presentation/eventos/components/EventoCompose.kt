package com.ricky.meupet.presentation.eventos.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.meupet.domain.MedicamentosParaSerAplicados
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.tipos.MedicamentoTipo
import com.ricky.meupet.ui.theme.MeuPetTheme

@Composable
fun EventoCompose(
    modifier: Modifier = Modifier,
    evento: MedicamentosParaSerAplicados
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = evento.dataAplicacao,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
            Box(modifier = Modifier.weight(1f)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight()
                        .size(48.dp)
                ) {
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(4.dp)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape,
                        modifier = Modifier
                            .size(48.dp)
                    ) {
                        Image(
                            imageVector = when (evento.medicamento.tipo) {
                                MedicamentoTipo.MEDICAMENTO ->  Icons.Default.MedicalInformation
                                MedicamentoTipo.VACINA ->  Icons.Default.Vaccines
                                MedicamentoTipo.VERMIFUGACAO -> Icons.Default.Medication
                            },

                            contentDescription = evento.medicamento.nome,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer)
                        )
                    }
                }

            }
            Text(
                text = evento.medicamento.nome,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.weight(4f)
            )
        }
    }
}

@Preview
@Composable
private fun EventosComposePreview() {
    MeuPetTheme {
        EventoCompose(
            evento = MedicamentosParaSerAplicados(
                Medicamento(nome = "Teste"),
                dataAplicacao = "24/11/1996"
            )
        )
    }
}