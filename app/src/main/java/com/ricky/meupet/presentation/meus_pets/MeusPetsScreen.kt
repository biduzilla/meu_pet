package com.ricky.meupet.presentation.meus_pets

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.meupet.R
import com.ricky.meupet.navigation.Screens
import com.ricky.meupet.presentation.meus_pets.components.CardAddPet
import com.ricky.meupet.ui.theme.MeuPetTheme
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeusPetsScreen(
    state: MeusPetsState,
    navController: NavController,
) {
    val context = LocalContext.current
//    state.pets.forEach {
//        deleteFileAtPath(it.pathFoto)
//    }

//    val dataSty = "02/09/2023"
//    val data = dataSty.convertToDate()
//    val calendar = Calendar.getInstance()
//
//    if (data != null) {
//        calendar.time = data
//        calendar.add(Calendar.DAY_OF_MONTH, -1)
//        val dataNoFuturo: Long = calendar.timeInMillis
//        NotificationService(context, "Teste Pet", "Bubu Linda").scheduleNotification(dataNoFuturo)
//
//        Log.i("infoteste", "MeusPetsScreen: ${Date(dataNoFuturo).convertToStringPrecisa()}")
//    }


    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = stringResource(id = R.string.meus_pets),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
            })

    }) { paddingValues ->
        LazyColumn(
            Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                CardAddPet(onClick = { navController.navigate(route = Screens.FormScreen.route) })
            }
            items(state.pets) { pet ->
                CardAddPet(
                    pet = pet,
                    onClick = {
                        navController.navigate(route = Screens.HomeScreen.route + "/${pet.id}")
//                        Toast.makeText(context, pet.nome, Toast.LENGTH_SHORT).show()
                    })
            }
        }

    }

}


fun deleteFileAtPath(filePath: String): Boolean {
    val fileToDelete = File(filePath)
    return fileToDelete.delete()
}

@Preview
@Composable
fun MeusPetsScreenPreview() {
    MeuPetTheme {
        val context: Context = LocalContext.current
        MeusPetsScreen(
            state = MeusPetsState(),
            navController = NavController(context),
        )
    }
}