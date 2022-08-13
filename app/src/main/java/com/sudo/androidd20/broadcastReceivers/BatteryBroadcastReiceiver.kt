package com.sudo.androidd20.broadcastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast
import com.sudo.androidd20.MainActivity

class BatteryBroadcastReceiver : BroadcastReceiver() {

    interface BatteryChangedListener {
        fun notifyBatteryChanged(currentBatteryLevel: Int)
    }

    //Thực hiện các hành động khi nhận được broadcast phản hồi từ hệ thống:
    override fun onReceive(context: Context?, intent: Intent?) {
        //Mặc dù khi registerReceiver đã xác định được bắt sự kiện do Action nào trả về rồi nhưng cần check xem có đúng không để tránh bị UnsafeProtectedBroadcastReceiver
        //- kiểu có luồng dữ liệu sai lệch không do hệ thống gửi trong khi thay đổi pin là Broadcast do hệ thống cung cấp
        when (intent?.action) {
            Intent.ACTION_BATTERY_CHANGED -> {
                val currentBatteryLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
                val batteryChangedListener = context as MainActivity

                Toast.makeText(
                    context,
                    "Current battery level: $currentBatteryLevel",
                    Toast.LENGTH_LONG
                )
                    .show()

                batteryChangedListener.notifyBatteryChanged(currentBatteryLevel)
            }
        }
    }
}