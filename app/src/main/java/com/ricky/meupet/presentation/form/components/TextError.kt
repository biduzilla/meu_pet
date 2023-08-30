package com.ricky.meupet.presentation.form.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.ricky.meupet.R
import com.ricky.meupet.ui.theme.ErrorLight


@Composable
fun TextError(isErro: Boolean, modifier: Modifier = Modifier) {
    if (isErro) {
        Text(
            modifier = modifier.fillMaxWidth(),
            text = stringResource(id = R.string.campo_obrigatorio),
            color = ErrorLight,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.labelMedium
        )
    }
}