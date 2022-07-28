package com.sudo.androidd20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sudo.androidd20.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingView = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(bindingView.root)
        bindingView.button.setOnClickListener {
            finish()
        }
    }
}