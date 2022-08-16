package com.sudo.androidd20

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.BatteryManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sudo.androidd20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: MyBroadcastReceiver
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

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
            registerReceiver(this.mBatteryReceiver,it)
        }
        binding.btRequestLocation.setOnClickListener {
            getLocation()
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        setContentView(binding.root)
    }

    private fun getLocation() {
        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }
        task.addOnSuccessListener {
            val locationStr = "Latitude: ${it.latitude} Longitude:${it.longitude}"
            if (it != null)
                binding.tvLocationGranted.text = locationStr
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

