package com.sudo.androidd20

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sudo.androidd20.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        binding.btnGet.setOnClickListener{
            fetchLocation()
        }
        val mBatteryReceiver = BatteryReceiver {
            binding.tvBattery.text = it.toString() + "%"
        }
        registerReceiver(mBatteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        setContentView(binding.root)
    }



    private fun fetchLocation(){
        val task = fusedLocationProviderClient.lastLocation
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }
        task.addOnCompleteListener {
            val location = "Latitude: ${it.result.latitude} Longitude: ${it.result.longitude}"
            if(it != null) binding.tvLocation.text = location
        }
    }

}