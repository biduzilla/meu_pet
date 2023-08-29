package com.ricky.meupet.presentation.meus_pets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ricky.meupet.R
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.ui.theme.MeuPetTheme

@Composable
fun CardAddPet(
    modifier: Modifier = Modifier,
    pet: Pet? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Row(
            Modifier
                .height(IntrinsicSize.Min)
                .padding(if (pet != null) 0.dp else 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            pet?.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it.pathFoto),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
                        .fillMaxHeight()
                        .padding(end = 16.dp)
                        .weight(2f)
                )
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .weight(4f)
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = it.nome,
                        style = MaterialTheme.typography.headlineSmall
                            .copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${it.tipo} - ${it.genero}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = it.idade,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } ?: run {
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(50.dp)
                        .weight(1f)
                )
                Column(
                    modifier = Modifier
                        .weight(5f)
                ) {
                    Text(
                        text = stringResource(id = R.string.cadastrar_pet),
                        style = MaterialTheme.typography.headlineSmall
                            .copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun GridCellAddPetPreview() {
    MeuPetTheme {
        CardAddPet(pet = Pet(), onClick = {})
    }
}