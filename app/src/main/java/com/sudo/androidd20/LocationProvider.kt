package com.sudo.androidd20

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import com.google.android.gms.location.LocationServices

const val REQUEST_CODE = 1

class LocationProvider(private val activity: Activity) {
    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)

    @SuppressLint("MissingPermission")
    fun whenLocationReceived(callback: (location: Location) -> Unit) {
        if (!isLocationGranted()) {
            requestLocationPermission()
            if (!isLocationGranted())
                return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            callback(it)
        }
    }

    private fun requestLocationPermission() {
        requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            REQUEST_CODE
        )
    }

    fun isLocationGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            activity.applicationContext,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ) == PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(
            activity.applicationContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}