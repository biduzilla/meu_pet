package com.ricky.meupet.presentation.config

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ricky.meupet.R
import com.ricky.meupet.domain.model.tipos.AnimalGenero
import com.ricky.meupet.domain.model.tipos.AnimalTipo
import com.ricky.meupet.navigation.Screens
import com.ricky.meupet.presentation.config.components.DialogRemoverPet
import com.ricky.meupet.presentation.form.components.DateDialiog
import com.ricky.meupet.presentation.form.components.DropdownCompose
import com.ricky.meupet.presentation.form.components.TextFieldCompose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigScreen(
    state: ConfigState,
    navController: NavController,
    onEvent: (ConfigEvents) -> Unit
) {
    val context = LocalContext.current
    val photoPicker =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                onEvent(ConfigEvents.SelectPhoto(it, context))
            })

    if (state.onErrorPhoto) {
        Toast.makeText(context, "Escolha uma foto", Toast.LENGTH_SHORT).show()
    }
    if (state.onErrorNascimento) {
        Toast.makeText(context, "Escolha uma data de nascimento", Toast.LENGTH_SHORT).show()
    }
    if (state.isOk) {
        Toast.makeText(context, "Pet Atualizado", Toast.LENGTH_SHORT).show()
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text(
                    text = stringResource(id = R.string.update_pet),
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.voltar)
                    )
                }
            },
        )
    }) { paddingValues ->
        if (state.isShowDialog) {
            DialogRemoverPet(
                onDimiss = { onEvent(ConfigEvents.DismissDialog) },
                onRemoverPet = {
                    onEvent(ConfigEvents.RemoverPet)
                    navController.navigate(Screens.MeusPetsScreen.route) {
                        popUpTo(Screens.ConfigScreen.route) {
                            inclusive = true
                        }
                    }
                })
        }
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier
                    .size(200.dp)
                    .clickable {
                        photoPicker.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {
                if (state.pathFoto.isBlank()) {
                    Image(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(model = state.tempPathFoto.ifBlank { state.pathFoto }),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextFieldCompose(
                value = state.nome,
                isError = state.onErrorNome,
                label = R.string.nome,
                onChange = { onEvent(ConfigEvents.OnChangeNome(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextFieldCompose(
                value = state.raca,
                label = R.string.raca,
                onChange = { onEvent(ConfigEvents.OnChangeRaca(it)) },
                isError = state.onErrorRaca
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextFieldCompose(
                value = state.peso,
                label = R.string.peso,
                keyboardType = KeyboardType.Decimal,
                onChange = { onEvent(ConfigEvents.OnChangePeso(it)) },
                isError = state.onErrorPeso
            )

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                onClick = {
                    onEvent(ConfigEvents.ShowDataPicker)
                },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = state.nascimento.ifBlank { stringResource(id = R.string.nascimento) },
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (state.isShowDataPicker) {
                DateDialiog(isPassado = true,
                    onDimiss = { onEvent(ConfigEvents.ShowDataPicker) },
                    onChangeDate = { onEvent(ConfigEvents.OnChangeDate(it)) })
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.tipo_animal),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            DropdownCompose(modifier = Modifier.fillMaxWidth(),
                label = state.tipo.name,
                list = AnimalTipo.values(),
                onChange = { onEvent(ConfigEvents.OnChangeTipo(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.sexo),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            DropdownCompose(modifier = Modifier.fillMaxWidth(),
                label = state.genero.name,
                list = AnimalGenero.values(),
                onChange = { onEvent(ConfigEvents.OnChangeGenero(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onEvent(ConfigEvents.AddPet) }) {
                Text(
                    text = stringResource(id = R.string.update_pet),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(ConfigEvents.ShowDataPicker) }) {
                Text(
                    text = stringResource(id = R.string.apagar_pet),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }

}