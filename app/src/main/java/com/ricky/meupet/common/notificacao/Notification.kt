package com.ricky.meupet.common.notificacao

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.ricky.meupet.MainActivity
import com.ricky.meupet.R
import java.util.Date

const val notificationID = 1
const val channelID = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class Notification : BroadcastReceiver() {
    companion object {
        @SuppressLint("ScheduleExactAlarm")
        fun scheduleNotification(
            title: String,
            message: String,
            data: Long,
            context: Context,
        ) {
            val intent = Intent(context, Notification::class.java)

            intent.putExtra(title, titleExtra)
            intent.putExtra(message, messageExtra)

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                notificationID,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                data,
                pendingIntent
            )

            val date = Date(data)
            val dateFormat =
                android.text.format.DateFormat.getLongDateFormat(context)
            val timeFormat =
                android.text.format.DateFormat.getTimeFormat(context)

            Log.i(
                "infoteste",
                "scheduleNotification: At:  ${dateFormat.format(date)} -- ${timeFormat.format(date)}"
            )
        }

    }

    override fun onReceive(context: Context, intent: Intent) {
        val resultIntent = Intent(context, MainActivity::class.java)
        val resultPendingIntent: PendingIntent? =
            TaskStackBuilder.create(context).run {
                addNextIntentWithParentStack(resultIntent)
                getPendingIntent(
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }

        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_pet)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .setContentIntent(resultPendingIntent)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)

    }
}

