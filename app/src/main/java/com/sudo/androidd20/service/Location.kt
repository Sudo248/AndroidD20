package com.sudo.androidd20.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class Location : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val location = intent?.getStringExtra("location")
        Toast.makeText(this, location,Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }

}