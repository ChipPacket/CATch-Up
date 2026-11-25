package com.example.catchup.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationManager

/*
    We send a notification to the user when triggered the alarm that we created.
    A notification is sent to the user when the alarm that we created is triggered.
 */
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val notificationManager =
                it.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val runnerNotifier = RunnerNotifier(notificationManager, it)

            runnerNotifier.showNotification()
        }
    }
}