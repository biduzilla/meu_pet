package com.ricky.meupet.presentation.form.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ricky.meupet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialiog(
    onDimiss: () -> Unit,
    onChangeDate: (Long) -> Unit,

    ) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = { onDimiss() },
        confirmButton = {
            Button(onClick = {
                datePickerState
                    .selectedDateMillis?.let { millis ->
                        onChangeDate(millis)
                        onDimiss()
                    }
            }) {
                Text(text = stringResource(id = R.string.escolher_data))
            }
        }) {
        DatePicker(state = datePickerState)
    }
}