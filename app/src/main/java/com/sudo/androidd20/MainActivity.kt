package com.sudo.androidd20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sudo.androidd20.constants.Constants
import com.sudo.androidd20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonLogout.setOnClickListener {
                logOutClicked()
            }
            tvGreeting.text = "Welcome, " +
                    intent.getStringExtra(Constants.USERNAME)
            tvPassword.text = "Last 3 digit of your password is: " +
                    intent.getStringExtra(Constants.SHORTPASSWORD)
        }
    }

    private fun logOutClicked() {
        finish()
    }
}