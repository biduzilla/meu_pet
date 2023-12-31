package com.ricky.meupet.presentation.vacinas.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.outlined.Vaccines
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.meupet.domain.model.Aplicacao
import com.ricky.meupet.domain.model.Medicamento
import com.ricky.meupet.domain.model.relationship.MedicamentoWithAplicacoes
import com.ricky.meupet.domain.model.tipos.MedicamentoTipo
import com.ricky.meupet.ui.theme.MeuPetTheme

@Composable
fun CardRemedio(
    modifier: Modifier = Modifier,
    medicamento: MedicamentoWithAplicacoes,
    icon: ImageVector = Icons.Default.Vaccines,
    onRemoverMedicamento: () -> Unit,
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    var isShowDialog by remember {
        mutableStateOf(false)
    }

    if (isShowDialog) {
        DialogRemoverMedicamento(onDimiss = { isShowDialog = false },
            onRemoverMedicamento = {
                onRemoverMedicamento()
                isShowDialog = false
            })
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
    ) {
        Box {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 8.dp
                    )
            ) {
                IconButton(
                    onClick = {
                        isShowDialog = true
                    },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    imageVector = when (medicamento.medicamento.tipo) {
                        MedicamentoTipo.MEDICAMENTO -> Icons.Default.MedicalInformation
                        MedicamentoTipo.VACINA -> Icons.Default.Vaccines
                        MedicamentoTipo.VERMIFUGACAO -> Icons.Default.Medication
                    },
                    contentDescription = medicamento.medicamento.nome,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .padding(16.dp)
                        .align(Alignment.CenterVertically)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = medicamento.medicamento.nome,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (medicamento.medicamento.tipo == MedicamentoTipo.VACINA) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                text = "Vacinado em ${medicamento.aplicacoes[0].data}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Revacinar em ${medicamento.aplicacoes[0].proximaAplicacao}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if (expanded) {
                        Text(
                            text = medicamento.medicamento.descricao,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                IconButton(onClick = {
                    expanded = !expanded
                }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardRemedioPreview() {
    MeuPetTheme {
        CardRemedio(
            medicamento = MedicamentoWithAplicacoes(
                Medicamento(nome = "teste"),
                aplicacoes = listOf(Aplicacao(data = "24/11/1996")),
            ),
            onRemoverMedicamento = {}
        )
    }
}