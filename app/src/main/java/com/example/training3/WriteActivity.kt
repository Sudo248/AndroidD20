package com.example.training3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.training3.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnLogOut.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}