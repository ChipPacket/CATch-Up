package com.example.catchup.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.NotificationManager
import android.util.Log

/*
    We send a notification to the user when triggered the alarm that we created.
    A notification is sent to the user when the alarm that we created is triggered.
 */
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("ALARM_TEST", "Receiver triggered")
        context?.let {
            val reminderName =
                intent?.getStringExtra("REMINDER_NAME")
                    ?: "Reminder"

            val notificationManager =
                it.getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager

            Log.d("ALARM_TEST", "About to show notification")
            RunnerNotifier(notificationManager, it, reminderName)
                .showNotification()
        }
    }
}