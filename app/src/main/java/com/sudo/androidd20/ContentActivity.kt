package com.sudo.androidd20

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_activity)
        val tv_user  = findViewById(R.id.tv_lineUser) as TextView
        val tv_pass = findViewById(R.id.tv_linepassword) as TextView
        val btn_logout = findViewById(R.id.btn_logout) as Button

        tv_user.text = intent?.getStringExtra("user").toString()
        tv_pass.text = intent?.getStringExtra("password").toString()
        btn_logout.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }


}