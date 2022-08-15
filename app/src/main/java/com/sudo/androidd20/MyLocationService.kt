package com.sudo.androidd20

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder


class MyLocationService :Service() {
    private val serviceBinder = LocalBinder()

    inner class LocalBinder:Binder() {
        internal val service:MyLocationService by lazy {
            this@MyLocationService
        }
    }

    override fun onBind(p0: Intent?): IBinder {
        stopForeground(true)
        return serviceBinder
    }

    override fun onRebind(intent: Intent?) {
        stopForeground(true)
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {

        return super.onUnbind(intent)
    }
}
