package com.sudo.androidd20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sudo.androidd20.databinding.ActivityInfoBinding

class Info : AppCompatActivity() {
    private lateinit var infoBinding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infoBinding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(infoBinding.root)

        val bundle: Bundle = intent.getBundleExtra("user")!!

        infoBinding.tvUserName.text = bundle.getString("userName").toString()
        infoBinding.tvPassword.text = bundle.getString("password").toString()

        infoBinding.btnLogOut.setOnClickListener {
            finish()
        }

    }
}