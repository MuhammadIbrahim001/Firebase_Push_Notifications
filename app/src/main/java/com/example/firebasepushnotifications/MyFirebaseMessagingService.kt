package com.example.firebasepushnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notification_channel"
const val channelName = "test_channel"

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (message.notification !==null){
            Log.v("notification",message.notification!!.title!!)
            genetrateNotification(message.notification!!.title!!, message.notification!!.body!! )
        }
    }
    fun genetrateNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, channelId)
                .setSmallIcon(androidx.loader.R.drawable.notification_icon_background)
                .setAutoCancel(true).setVibrate(
                    longArrayOf(1000, 1000, 1000, 1000)
                ).setOnlyAlertOnce(true).setContentIntent(pendingIntent)
        builder = builder.setContent(getRemoteView(title, message))
        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel= NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }

    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteViews =
            RemoteViews("com.example.firebasepushnotifications", R.layout.notification)
        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.desc, message)
        remoteViews.setImageViewResource(R.id.app_logo, R.drawable.ic_launcher_background)
        return remoteViews
    }
}