package com.sudo.androidd20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val tv_name= findViewById(R.id.tvName) as TextView
        val tv_pass = findViewById(R.id.tvPass) as TextView
        val btn_logout = findViewById(R.id.btnLogout) as Button
        tv_name.setText(intent?.getStringExtra("name").toString())
        tv_pass.setText(intent?.getStringExtra("password").toString())
        btn_logout.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}