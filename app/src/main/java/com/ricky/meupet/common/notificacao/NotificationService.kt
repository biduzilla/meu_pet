package com.ricky.meupet.common.notificacao

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.ricky.meupet.R
import kotlin.random.Random


class NotificationService(
    private val context: Context,
    private val title:String,
    private val message:String
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    fun basicNotification() {
        val notification = NotificationCompat.Builder(context, "pet_reminder")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_pet)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
}

