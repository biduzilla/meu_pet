package com.ricky.meupet.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.convertToString(): String {
    return SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    ).format(this)
}