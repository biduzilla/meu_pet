package com.ricky.meupet.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Date.convertToString(): String {
    return SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    ).format(this)
}

fun Date.convertToStringPrecisa(): String {
    return SimpleDateFormat(
        "dd/MM/yyyy HH:mm",
        Locale.getDefault()
    ).format(this)
}

fun Date.dataParaDataEspecifica(dia: Int, hora: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_MONTH, dia)
    calendar.set(Calendar.HOUR_OF_DAY, hora)

    return calendar.time
}

fun Date.dataParaLongEspecifica(dia: Int, hora: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DAY_OF_MONTH, dia)
    calendar.set(Calendar.HOUR_OF_DAY, hora)
    return calendar.timeInMillis

}