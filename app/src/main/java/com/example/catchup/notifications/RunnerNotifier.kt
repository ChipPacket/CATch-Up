package com.example.catchup.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

/*
    The Notifier class is an abstract class that
    provides the necessary information to create a
    notification and includes abstract methods that
    must be implemented by its subclasses.
 */
abstract class Notifier(
    private val notificationManager: NotificationManager
) {
    abstract val notificationChannelId: String
    abstract val notificationChannelName: String
    abstract val notificationId: Int

    fun showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = createNotificationChannel()
            notificationManager.createNotificationChannel(channel)
        }
        val notification = buildNotification()
        notificationManager.notify(
            notificationId,
            notification
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    open fun createNotificationChannel(
        importance: Int = NotificationManager.IMPORTANCE_DEFAULT
    ): NotificationChannel {
        return NotificationChannel(
            notificationChannelId,
            notificationChannelName,
            importance
        )
    }

    abstract fun buildNotification(): Notification
    protected abstract fun getNotificationTitle(): String
    protected abstract fun getNotificationMessage(): String
}

/*
    The RunnerNotifier class is a helper class derived from the Notifier class.
    It is used to configure the necessary settings for sending a run notification to the user.
 */
class RunnerNotifier(
    private val notificationManager: NotificationManager,
    private val context: Context
) : Notifier(notificationManager) {

    override val notificationChannelId: String = "notif_channel_id"
    override val notificationChannelName: String = "Reminder Notification"
    override val notificationId: Int = 200

    override fun buildNotification(): Notification {
        return NotificationCompat.Builder(context, notificationChannelId)
            .setContentTitle(getNotificationTitle())
            .setContentText(getNotificationMessage())
            .setSmallIcon(android.R.drawable.btn_star)
            .build()
    }

    override fun getNotificationTitle(): String {
        return "Time to go for a run 🏃‍️"
    }

    override fun getNotificationMessage(): String {
        return "You are ready to go for a run?"
    }
}