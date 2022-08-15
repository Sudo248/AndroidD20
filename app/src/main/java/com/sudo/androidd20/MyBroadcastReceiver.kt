package com.sudo.androidd20;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    var per: Int = 0
    override fun onReceive(p0: Context?, p1: Intent?) {
        per = p1?.getIntExtra(BatteryManager.EXTRA_LEVEL, 100)!!
        Toast.makeText(p0!!, "Battery $per", Toast.LENGTH_LONG).show()
    }
}
