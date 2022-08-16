package com.sudo.androidd20

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.sudo.androidd20.broadcastReceivers.BatteryBroadcastReceiver
import com.sudo.androidd20.databinding.ActivityMainBinding
import com.sudo.androidd20.service.Location

class MainActivity : AppCompatActivity() {

    private lateinit var curruntLocation: String
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermission()
        getLocation()

        binding.btCurrentLocation.setOnClickListener() {
            val intent = Intent(this, Location::class.java)
            intent.putExtra("location", curruntLocation)
            startService(intent)
        }

        binding.btCurrentBattery.setOnClickListener() {
            registerReceiver(
                BatteryBroadcastReceiver(),
                IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            )
        }

    }

    private fun requestPermission() {
        val locationPermissionRequest =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    when {
                        permissions.getOrDefault(
                            android.Manifest.permission.ACCESS_FINE_LOCATION,
                            false
                        ) -> {

                        }
                        permissions.getOrDefault(
                            android.Manifest.permission.ACCESS_COARSE_LOCATION,
                            false
                        ) -> {
                        }
                        else -> {
                        }
                    }
                }
            }
        locationPermissionRequest.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    @SuppressLint("SetTextI18n")
    private fun getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            curruntLocation = "Latitude: ${location.latitude}\nLongitude: ${location.longitude}"
        }
    }

}