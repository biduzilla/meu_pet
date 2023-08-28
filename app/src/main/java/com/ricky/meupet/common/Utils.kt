package com.ricky.meupet.common

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.Date

fun calculateAgeAndMonths(birthDate: Date): String {
    val currentDate = LocalDate.now()
    val localBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val period = Period.between(localBirthDate, currentDate)

    val years = period.years
    val months = period.months
    val days = period.days

    return if (years == 0 && months == 0) "$days dias" else "$months meses e $years anos"
}

fun getFilePathFromContentUri(context: Context, contentUri: Uri): String? {
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(contentUri, projection, null, null, null)

    cursor?.use {
        val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        it.moveToFirst()
        return it.getString(columnIndex)
    }

    return null
}

fun convertContentUriToFileUrl(context: Context, contentUri: Uri): String? {
    val filePath = getFilePathFromContentUri(context, contentUri)
    return if (!filePath.isNullOrBlank()) {
        val file = File(filePath)
        file.toURI().toString()
    } else {
        null
    }
}
