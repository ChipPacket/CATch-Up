package com.example.catchup.notifications

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.example.catchup.database.Reminder
import kotlinx.serialization.Contextual
import java.util.Date
import kotlin.jvm.java

interface AlarmScheduler {
    fun createPendingIntent(reminderItem: Reminder): PendingIntent
    fun schedule(reminderItem: Reminder)
    fun cancel(reminderItem: Reminder)
}

/*
    The NotificationAlarmScheduler is a class for scheduling and cancelling alarms.

    The purpose of implementing the NotificationAlarmScheduler class via the AlarmScheduler interface
    is to allow this class to adapt to different scenarios in the future.

    At the same time, it increases the testability of the ViewModel and makes the code more flexible
    when I want to use it within the ViewModel.
 */
class NotificationAlarmScheduler(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

    // create and intent that targets alarm receiver using createPendingIntent
    override fun createPendingIntent(reminderItem: Reminder): PendingIntent {
        val intent = Intent(
            context,
            AlarmReceiver::class.java
        ).apply {
            putExtra("REMINDER_NAME", reminderItem.name)
        }

        return PendingIntent.getBroadcast(
            context,
            reminderItem.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                    PendingIntent.FLAG_IMMUTABLE
        )
    }

    /*
        The schedule method takes ReminderItem as a parameter and
        schedules a notification to repeat daily at the same time,
        based on the time information specified in the object.
        Different parameters can be used for the repetition time.
     */
    //     @RequiresPermission(Manifest.permission.SCHEDULE_EXACT_ALARM)
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("ScheduleExactAlarm")
    override fun schedule(reminderItem: Reminder) {
        Log.e(
            "ALARM_TEST",
            "Scheduling alarm for ${Date(reminderItem.timeMillis)}"
        )

        Log.e(
            "ALARM_TEST",
            "canScheduleExactAlarms = ${alarmManager.canScheduleExactAlarms()}"
        )

        if (alarmManager.canScheduleExactAlarms()) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                reminderItem.timeMillis,
                createPendingIntent(reminderItem)
            )
        } else {
            Log.e(
                "ALARM_TEST",
                "Exact alarms not permitted"
            )
        }
    }

    // cancel scheduled alarm
    override fun cancel(reminderItem: Reminder) {
        alarmManager.cancel(
            createPendingIntent(reminderItem)
        )
    }

}

