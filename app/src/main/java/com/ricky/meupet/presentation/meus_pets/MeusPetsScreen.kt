package com.ricky.meupet.presentation.meus_pets

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.meupet.R
import com.ricky.meupet.presentation.meus_pets.components.GridCellAddPet
import com.ricky.meupet.ui.theme.MeuPetTheme

@Composable
fun MeusPetsScreen(
    state: MeusPetsState,
    navController: NavController,
    onEvent: (MeusPetsEvent) -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(10) {
                    GridCellAddPet(modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun MeusPetsScreenPreview() {
    MeuPetTheme {
        val context: Context = LocalContext.current
        MeusPetsScreen(
            state = MeusPetsState(),
            navController = NavController(context),
            onEvent = {})
    }
}