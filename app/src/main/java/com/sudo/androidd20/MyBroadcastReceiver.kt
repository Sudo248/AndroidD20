package com.sudo.androidd20;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

class MyBroadcastReceiver : BroadcastReceiver() {
    var per: Int = 0
    override fun onReceive(p0: Context?, p1: Intent?) {
        per = p1?.getIntExtra(BatteryManager.EXTRA_LEVEL, 100)!!
    }
}
