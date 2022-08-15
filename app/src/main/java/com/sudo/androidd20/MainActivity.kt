package com.sudo.androidd20

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sudo.androidd20.broadcastReceivers.BatteryBroadcastReceiver
import com.sudo.androidd20.databinding.ActivityMainBinding
import com.sudo.androidd20.service.Location

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val intent = Intent(this, Location::class.java)
        binding.btCurrentLocation.setOnClickListener(){
            startService(intent)
        }
        binding.btCurrentBattery.setOnClickListener(){
            registerReceiver(BatteryBroadcastReceiver(), IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        }
    }

}