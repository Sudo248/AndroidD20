package com.sudo.androidd20.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.sudo.androidd20.others.Const.CHANNEL_ID
import com.sudo.androidd20.R

class LocationNotificationService : Service() {
    override fun onCreate() {
        Log.i("Services", "onCreate")
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendPushNotification()

        //khi bị hệ thống kill thì không cần khởi động lại service
        return START_NOT_STICKY
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sendPushNotification() {

        //tạo view cho PushNotification:
        val remoteViews = RemoteViews(packageName, R.layout.custom_notification)
        remoteViews.setTextViewText(R.id.tvContentText, "Brruh bruh lmao")
        remoteViews.setImageViewResource(R.id.imbtnHideNotification, R.drawable.ic_round_close_24)

        val locationNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_round_close_24)
            .setCustomContentView(remoteViews)
            .setAutoCancel(true)
            .build()

        startForeground(1, locationNotification)
    }

    override fun onDestroy() {
        Log.i("Service", "onDestroy")
        super.onDestroy()
    }
}