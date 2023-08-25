package com.ricky.meupet.presentation.meus_pets.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.ricky.meupet.domain.model.Pet
import com.ricky.meupet.ui.theme.MeuPetTheme
import java.io.File

@Composable
fun GridCellAddPet(
    modifier: Modifier = Modifier,
    pet: Pet? = null
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .width(100.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        pet?.let {
            Image(
                rememberAsyncImagePainter(File(it.pathFoto)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        } ?: kotlin.run {
            Image(
                imageVector = Icons.Default.Pets, contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }

    }
}

@Preview
@Composable
private fun GridCellAddPetPreview() {
    MeuPetTheme {
        GridCellAddPet()
    }
}