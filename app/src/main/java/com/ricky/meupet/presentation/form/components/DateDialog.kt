package com.ricky.meupet.presentation.form.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ricky.meupet.R
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateDialiog(
    isPassado: Boolean,
    onDimiss: () -> Unit,
    onChangeDate: (Long) -> Unit,
) {
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            val currentTime = Calendar.getInstance().timeInMillis
            return if (isPassado) utcTimeMillis < currentTime
            else utcTimeMillis >= currentTime
        }
    })

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
        DatePicker(state = datePickerState,
            title = null,
            headline = {
                Text(
                    text = stringResource(id = R.string.escolher_data),
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, top = 8.dp)
                )
            })
    }
}