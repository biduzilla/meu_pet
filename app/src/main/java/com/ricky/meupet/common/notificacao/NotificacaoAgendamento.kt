package com.ricky.meupet.common.notificacao

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ricky.meupet.common.Constants

class NotificacaoAgendamento : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val title = intent?.getStringExtra(Constants.titleExtra)
        val message = intent?.getStringExtra(Constants.messageExtra)

        if (title != null && context != null && message != null) {
            NotificationService(context).basicNotification(title, message)

        }
    }
}