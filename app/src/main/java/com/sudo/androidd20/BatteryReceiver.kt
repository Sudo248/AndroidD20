package com.sudo.androidd20

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager

class BatteryReceiver(val callback:(capacity: Int)-> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val capacity = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
        capacity?.let { callback(it) }
    }

}

