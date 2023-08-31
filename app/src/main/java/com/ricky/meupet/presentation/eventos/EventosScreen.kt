package com.ricky.meupet.presentation.eventos

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ricky.meupet.R
import com.ricky.meupet.presentation.eventos.components.EventoCompose

@Composable
fun EventosScreen(
    petId: String? = null,
    state: EventosState,
    onEvent: (EventosEvent) -> Unit
) {
    var petIdLoad by remember{ mutableStateOf(false) }
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            petId?.let {
                if(!petIdLoad){
                    onEvent(EventosEvent.OnGetPetId(it))
                    petIdLoad = true
                }

            }
            Text(
                text = stringResource(id = R.string.prox_eventos),
                style = MaterialTheme.typography.headlineLarge,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(state.medicamentos) { item ->
            EventoCompose(evento = item)
        }
    }
}