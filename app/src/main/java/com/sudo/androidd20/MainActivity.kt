package com.sudo.androidd20

import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.sudo.androidd20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var batteryListener: BatteryListener

    private val locationProvider: LocationProvider by lazy {
        LocationProvider(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        setLocationGrantedText(locationProvider.isLocationGranted())
        setBatteryListener()
    }

    private fun setClickListeners() {
        binding.btRequestLocation.setOnClickListener {
            requestLocationClicked()
        }
        binding.btOpenAppSetting.setOnClickListener {
            openAppSetting()
        }
    }

    private fun requestLocationClicked() {
        updateLocationTextViews()
    }

    private fun updateLocationTextViews() {
        setLocationGrantedText(locationProvider.isLocationGranted())

        locationProvider.whenLocationReceived {
            setLocationText(it)
        }
    }

    private fun setLocationGrantedText(boolean: Boolean) {
        binding.tvIsLocationGranted.text = getString(R.string.location_granted, boolean.toString())
    }

    private fun setLocationText(location: Location?) {
        location?.let {
            val lat = location.latitude
            val lon = location.longitude
            binding.tvLocation.text = getString(R.string.location, lat, lon)
        }
    }

    private fun openAppSetting() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
            Uri.fromParts("package", packageName, null)
        )
        startActivity(intent)
    }

    private fun setBatteryListener() {
        batteryListener = BatteryListener {
            binding.tvBatteryLevel.text = getString(R.string.battery_level, it)
        }
        registerReceiver(batteryListener, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }
}