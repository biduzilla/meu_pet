package com.ricky.meupet.presentation.form.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ricky.meupet.R
import com.ricky.meupet.ui.theme.ErrorLight


@Composable
fun TextError(isErro: Boolean) {
    if (isErro) {
        Text(
            text = stringResource(id = R.string.campo_obrigatorio),
            color = ErrorLight
        )
    }
}