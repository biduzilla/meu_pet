package com.ricky.meupet.common.notificacao

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import com.ricky.meupet.R
import com.ricky.meupet.common.Constants.messageExtra
import com.ricky.meupet.common.Constants.titleExtra
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.random.Random


class NotificationService(
    private val context: Context,
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun basicNotification(
        title: String,
        message: String
    ) {
        val notification = NotificationCompat.Builder(context, "pet_reminder")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_pet)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(false)
            .setBadgeIconType(R.drawable.ic_pet)
            .setColor(Color.BLUE)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun scheduleNotification(
        date: Long,
        title: String,
        message: String
    ) {
        val intent = Intent(context, NotificacaoAgendamento::class.java)
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager.set(AlarmManager.RTC_WAKEUP, date, pendingIntent)
    }
}

