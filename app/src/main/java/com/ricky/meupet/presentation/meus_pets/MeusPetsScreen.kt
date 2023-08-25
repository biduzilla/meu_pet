package com.ricky.meupet.presentation.meus_pets

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.meupet.R
import com.ricky.meupet.navigation.Screens
import com.ricky.meupet.presentation.meus_pets.components.CardAddPet
import com.ricky.meupet.ui.theme.MeuPetTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeusPetsScreen(
    state: MeusPetsState,
    navController: NavController,
) {
    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.padding(16.dp), title = {
            Text(
                text = stringResource(id = R.string.meus_pets),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
        })

    }) { paddingValues ->
        LazyColumn(
            Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                CardAddPet(onClick = { navController.navigate(route = Screens.FormScreen.route) })
            }
            items(state.pets) { pet ->
                CardAddPet(onClick = { navController.navigate(route = Screens.HomeScreen.route + "/${pet.id}") })
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
            navController = NavController(context)
        )
    }
}