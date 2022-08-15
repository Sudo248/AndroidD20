package com.sudo.androidd20

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager

class BatteryReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val level = p1?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val batteryShow = "Battery Status: $level %"

    }
}