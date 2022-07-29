package com.sudo.androidd20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import com.sudo.androidd20.databinding.ActivityMain2Binding
import com.sudo.androidd20.databinding.ActivityMain2Binding.inflate
import com.sudo.androidd20.databinding.ActivityMainBinding.inflate
import com.sudo.androidd20.databinding.FragmentLoginBinding.inflate
import com.sudo.androidd20.databinding.FragmentSignUpBinding.inflate

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvName.text = intent?.getStringExtra("name").toString()
        binding.tvPass.text = intent?.getStringExtra("password").toString()
        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}