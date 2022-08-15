package com.sudo.androidd20

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager

class BatteryListener(private val callback: (percentage: Int) -> Unit) : BroadcastReceiver() {
    override fun onReceive(p0: Context?, intent: Intent) {
        val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        callback(level)
    }

}