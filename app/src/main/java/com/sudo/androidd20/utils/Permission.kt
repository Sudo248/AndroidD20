package com.sudo.androidd20.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.sudo.androidd20.MainActivity

object Permission {
    private const val REQUEST_CODE_PERMISSION: Int = 1

    @SuppressLint("ObsoleteSdkInt")
    fun isGrantedLocationPermission(context: Context): Boolean {
        //Từ android 6 trở lên cần xin 1 số quyền từ người dùng thay vì xin trực tiếp từ manifest
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            true
        } else {
            context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun grantLocationPermission(context: Context) {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        (context as MainActivity).requestPermissions(permissions, REQUEST_CODE_PERMISSION)
    }
}