package com.sudo.androidd20.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast

class BatteryBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val currentBattery = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
        Toast.makeText(context, "Current battery: $currentBattery", Toast.LENGTH_SHORT).show()
    }
}