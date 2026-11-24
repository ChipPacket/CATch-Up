package com.example.catchup.notifications


/* import kotlin.jvm.java
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.catchup.ui.screens.AppIcons */

// https://itnext.io/understanding-notifications-in-android-a-developers-guide-fce100810f1c

/*
class NotificationUtil(var name: String, var description: String){
    companion object {
        const val NOTIFICATION_CHANNEL = "notification_channel"
        const val NOTIFICATION_ID = "notification_id"
        const val NOTIFICATION_NAME = "notification_name"
    }

    // creates notification, pass in reminders name n description
    fun showNotification(context: Context, id: Int){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        // create notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL,
                    NOTIFICATION_NAME,
                    NotificationManager.IMPORTANCE_HIGH)

            // use the default notification ringer on device
            val ringtoneManager = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            // add audio attributes
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            // add vibration #swag
            channel.apply {
                lockscreenVisibility = Notification.VISIBILITY_PUBLIC;
                enableVibration(true)
                setSound(ringtoneManager, audioAttributes)
            }

            notificationManager.createNotificationChannel(channel)
        }

        // create pending intent
        //val intent = Intent(this, ResultActivity::class.java)


        // create notification
        val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
            .setContentTitle(name)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            // needs  .setContentIntent(pendingIntent)

        // post notification
        notificationManager.notify(id, notification.build())
        Log.d("ReminderWorker", "Reminder name $name, description $description")


    }


}



private const val CHANNEL_ID = "reminder_notif"

// notification channel
fun createNotificationChannel(context: Context) {
    // val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Reminder Notification",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notifications reminder going off"
        }

        val notificationManager =
            context.getSystemService(NotificationManager::class.java)

        notificationManager.createNotificationChannel(channel)
    }
}

// BUILD NOTIFICATION
/*
fun buildNotification(context: Context): Notification {
    val intent = Intent(context, OrderDetailsActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )


    return NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("Order update")
        .setContentText("Your order is on the way")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
        .build()
}

 */

 */



