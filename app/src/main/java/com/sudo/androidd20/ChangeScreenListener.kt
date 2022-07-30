package com.sudo.androidd20

interface ChangeScreenListener {
    fun onChangeFragment(fragment: androidx.fragment.app.Fragment)
    fun changeActivity(activity: androidx.appcompat.app.AppCompatActivity)
}