package com.ricky.meupet.presentation.meus_pets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.ricky.meupet.R
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.ui.theme.MeuPetTheme
import java.io.File

@Composable
fun CardAddPet(
    modifier: Modifier = Modifier,
    pet: Pet? = null,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick },
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(50.dp)
    ) {
        Row(
            Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            pet?.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it.pathFoto),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(50.dp)
                        .padding(8.dp)
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = it.nome,
                        style = MaterialTheme.typography.headlineSmall
                            .copy(fontWeight = FontWeight.Bold)
                    )

                    Text(
                        text = " ${it.tipo} - ${it.genero}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = it.idade,
                        style = MaterialTheme.typography.headlineSmall
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
                        .padding(8.dp)
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
        CardAddPet(onClick = {})
    }
}