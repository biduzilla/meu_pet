package com.ricky.meupet.common

import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.Date

fun calculateAgeAndMonths(birthDate: Date): Pair<Int, Int> {
    val currentDate = LocalDate.now()
    val localBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val period = Period.between(localBirthDate, currentDate)

    val years = period.years
    val months = period.months

    return years to months
}