package com.ricky.meupet.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.domain.model.tipos.AnimalGenero
import com.ricky.meupet.ui.theme.MeuPetTheme
import java.math.BigDecimal

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    pet: Pet,
    label: String,
    isDark: Boolean,
    onPressVoltar: () -> Unit,
    onChangeTheme: (Boolean) -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(onClick = { onPressVoltar() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )

                }
                Text(
                    text = label,
                    style = MaterialTheme.typography.headlineMedium
                        .copy(fontWeight = FontWeight.Bold)
                )
                Switch(
                    checked = isDark,
                    onCheckedChange = {
                        onChangeTheme(it)
                    },
                    thumbContent = {
                        if (isDark) {
                            Icon(
                                imageVector = Icons.Default.DarkMode,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.LightMode,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 32.dp)
            ) {
                Card(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(100.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = pet.pathFoto),
                        contentDescription = pet.nome,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Column(
                    Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = pet.nome,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "${pet.raca} - ${pet.genero}",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = pet.idade,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "${pet.peso} Kg",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TopBarPreview() {
    MeuPetTheme {
        TopBar(label = "Eventos",
            pet = Pet(
                nome = "teste",
                raca = "vira lata",
                genero = AnimalGenero.FEMEA,
                idade = "1 ano e 3 meses",
                peso = BigDecimal(4.5)
            ), isDark = false, onPressVoltar = {}, onChangeTheme = {})
    }
}