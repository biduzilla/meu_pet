package com.ricky.meupet.presentation.form

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ricky.meupet.R
import com.ricky.meupet.domain.model.tipos.AnimalGenero
import com.ricky.meupet.domain.model.tipos.AnimalTipo
import com.ricky.meupet.presentation.form.components.DateDialiog
import com.ricky.meupet.presentation.form.components.DropdownCompose
import com.ricky.meupet.presentation.form.components.TextFieldCompose
import com.ricky.meupet.ui.theme.MeuPetTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    state: FormState,
    navController: NavController,
    onEvent: (FormEvent) -> Unit
) {

    val context = LocalContext.current
    val photoPicker =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                onEvent(FormEvent.SelectPhoto(it, context))
            })

    if (state.onErrorPhoto) {
        Toast.makeText(context, "Escolha uma foto", Toast.LENGTH_SHORT).show()
    }
    if (state.onErrorNascimento) {
        Toast.makeText(context, "Escolha uma data de nascimento", Toast.LENGTH_SHORT).show()
    }
    if (state.isOk) {
        navController.popBackStack()
    }
    Scaffold(topBar = {
        CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ), title = {
            Text(
                text = stringResource(id = R.string.cadastrar_pet),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.voltar),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
            actions = {
                IconButton(onClick = {
                    onEvent(FormEvent.AddPet)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = stringResource(id = R.string.salvar_pet),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        )
    }) { paddingValues ->
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
                elevation = CardDefaults.elevatedCardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                if (state.pathFoto.isBlank()) {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(model = state.pathFoto),
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
                onChange = { onEvent(FormEvent.OnChangeNome(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextFieldCompose(
                value = state.raca,
                label = R.string.raca,
                onChange = { onEvent(FormEvent.OnChangeRaca(it)) },
                isError = state.onErrorRaca
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextFieldCompose(
                value = state.peso,
                label = R.string.peso,
                keyboardType = KeyboardType.Decimal,
                onChange = { onEvent(FormEvent.OnChangePeso(it)) },
                isError = state.onErrorPeso
            )

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                onClick = {
                    onEvent(FormEvent.ShowDataPicker)
                },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = state.nascimento.ifBlank { stringResource(id = R.string.nascimento) },
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (state.isShowDataPicker) {
                DateDialiog(isPassado = true,
                    onDimiss = { onEvent(FormEvent.ShowDataPicker) },
                    onChangeDate = { onEvent(FormEvent.OnChangeDate(it)) })
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.tipo_animal),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            DropdownCompose(modifier = Modifier.fillMaxWidth(),
                label = state.tipo.name,
                list = AnimalTipo.values(),
                onChange = { onEvent(FormEvent.OnChangeTipo(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.sexo),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            DropdownCompose(modifier = Modifier.fillMaxWidth(),
                label = state.genero.name,
                list = AnimalGenero.values(),
                onChange = { onEvent(FormEvent.OnChangeGenero(it)) }
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview
@Composable
private fun FormScreenPreview() {
    MeuPetTheme {
        val context = LocalContext.current
        FormScreen(state = FormState(), navController = NavController(context), onEvent = {})
    }
}