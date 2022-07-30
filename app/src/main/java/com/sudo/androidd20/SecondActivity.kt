package com.sudo.androidd20

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sudo.androidd20.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingView = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bindingView.root)
        val intent = intent
        val name = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        bindingView.tvLineUser.text = name
        bindingView.tvPassword.text = password.toString()
        bindingView.btnLogOut.setOnClickListener {
            Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}