package com.ricky.meupet.common

import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String.convertToDate(): Date? {
    return SimpleDateFormat(
        "d/M/yyyy",
        Locale.getDefault()
    ).parse(this)
}

fun String.convertToZoneDateTime(): ZonedDateTime {
    val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
    return ZonedDateTime.parse(this, formatter)
}