package com.sudo.androidd20

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        setContentView(binding.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        requestLocationPermission()
        registerReceiver(this.batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private fun requestLocationPermission() {
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
                101
            )
            return
        }

        binding.buttonBasic.setOnClickListener {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                val locationStr = "Latitude: ${it.latitude} Longitude:${it.longitude}"
                if (it != null) {
                    Toast.makeText(this, locationStr, Toast.LENGTH_LONG).show()
                    binding.tvLocation.text = locationStr
                } else
                    Toast.makeText(this, "No location found", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val batteryReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val level = intent.getIntExtra("level", 0)
            val bat = "$level%"
            binding.tvBattery.text = bat
        }
    }
}