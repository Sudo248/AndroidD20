package com.sudo.androidd20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.room.Room
import com.sudo.androidd20.data.User
import com.sudo.androidd20.data.UserDataBase
import com.sudo.androidd20.databinding.ActivityLoginBinding
import com.sudo.androidd20.fragment.LogInFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    companion object {
        lateinit var listUser: MutableList<User>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            add<LogInFragment>(R.id.fragment_container)
        }

        listUser = mutableListOf(
            User(
                email = "sankatana02@gmail.com",
                userName = "S4ltF1sh",
                password = "123456"
            )
        )
    }
}