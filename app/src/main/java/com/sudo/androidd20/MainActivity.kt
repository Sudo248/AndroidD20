package com.sudo.androidd20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    val loginInFragment= LoginFragment()
   // val signUpFragment= SignUpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragment_menu,loginInFragment)
            commit()
        }
    }
}