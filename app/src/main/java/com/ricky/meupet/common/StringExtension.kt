package com.ricky.meupet.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertToDate(): Date? {
    return SimpleDateFormat(
        "d/M/yyyy",
        Locale.getDefault()
    ).parse(this)
}