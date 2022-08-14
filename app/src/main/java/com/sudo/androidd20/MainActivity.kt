package com.sudo.androidd20

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.sudo.androidd20.broadcastReceivers.BatteryBroadcastReceiver
import com.sudo.androidd20.services.LocationNotificationService
import com.sudo.androidd20.utils.Permission
import com.sudo.androidd20.databinding.ActivityMainBinding
import com.sudo.androidd20.dialogs.RequestOpenGpsDialog
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class MainActivity : AppCompatActivity(), BatteryBroadcastReceiver.BatteryChangedListener {

    private lateinit var binding: ActivityMainBinding
    private var isStartedLocationService = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClicked()

        //Từ Android 8 (Oreo)
        registerReceiver(BatteryBroadcastReceiver(), IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private fun setOnClicked() {
        binding.btnGetLocation.setOnClickListener {
            getLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        //Check nếu chưa cấp quyền truy cập vị trí sẽ yêu cầu cấp quyền:
        if (!Permission.isGrantedLocationPermission(this))
            Permission.grantLocationPermission(this)
        else {
            if (!isStartedLocationService)
                startService(Intent(this, LocationNotificationService::class.java))

            //Mở hộp thoại yêu cầu người dùng mở GPS:
            requestTurnGpsOn()


            //Đoạn này em xem trên android.dev là để lấy vị trí ;-;
            val fusedLocationClient: FusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this)

            fusedLocationClient.lastLocation.addOnCompleteListener(this) {
                val location = it.result
                if (location != null)
                    binding.tvLocation.text = location.run {
                        "Kinh độ: $longitude\nVĩ độ: $latitude"
                    }
            }
        }
    }

    //Kiểm tra khi người dùng chọn lựa có cấp quyền không:
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //permission granted
        } else {
            //permission denied
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    //Cái này em xem trên stackoverflow:
    private fun requestTurnGpsOn() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var gpsEnabled = false
        var networkEnabled = false

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (!gpsEnabled && !networkEnabled) {
            RequestOpenGpsDialog(this).create().show()
        }
    }

    override fun onDestroy() {
        //Tắt đi nếu không muốn bị chạy ngầm :v
        stopService(Intent(this, LocationNotificationService::class.java))
        unregisterReceiver(BatteryBroadcastReceiver())
        super.onDestroy()
    }

    //Override lại thằng notifyBatteryChanged của BatteryBroadcastReceiver để update pin hiển thị trên màn hình
    @SuppressLint("SetTextI18n")
    override fun notifyBatteryChanged(currentBatteryLevel: Int) {
        binding.tvCurrentBatteryLevel.text = "$currentBatteryLevel%"
    }
}