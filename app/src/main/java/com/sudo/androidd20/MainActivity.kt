package com.sudo.androidd20

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.sudo.androidd20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: MyBroadcastReceiver
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiver = MyBroadcastReceiver()
        /*
            IntentFilter rất hữu ích để xác định ứng dụng nào muốn nhận ý định nào,
            vì ở đây chúng tôi muốn phản hồi việc thay đổi chế độ trên máy bay
             */
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            /*
            đăng ký tham số receiver it được truyền vào hàm registerReceiver () là IntentFilter mà chúng ta vừa tạo
             */
            registerReceiver(receiver,it)
        }
        IntentFilter(Intent.ACTION_BATTERY_CHANGED).also {
            registerReceiver(mBatteryReceiver,it)
        }
    }
    private val mBatteryReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val level = p1?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val batteryShow = "Phần trăm pin là $level %"
            binding.tvBatteryLevel.text = batteryShow
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
        unregisterReceiver(mBatteryReceiver)
    }
}

