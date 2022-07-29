package com.sudo.androidd20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.sudo.androidd20.data.User
import com.sudo.androidd20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setDataDisplayed()
        setOnClick()
    }

    private fun setDataDisplayed() {
        val userReceived = intent?.extras?.get("userData") as User

        binding.apply {
            tvUsername.text = userReceived.userName
            tvName.text = userReceived.name
            tvAge.text = userReceived.age.toString()
        }
    }

    private fun setOnClick() {
        binding.btnLogOut.setOnClickListener {
            finish()
        }
    }
}