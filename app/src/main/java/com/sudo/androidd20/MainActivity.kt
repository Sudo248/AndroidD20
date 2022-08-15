package com.sudo.androidd20

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.sudo.androidd20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: MyBroadcastReceiver
    private lateinit var mBatteryReceiver: BatteryReceiver
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiver = MyBroadcastReceiver()
        mBatteryReceiver = BatteryReceiver()
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

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}