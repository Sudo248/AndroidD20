package com.sudo.androidd20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sudo.androidd20.Navigator
import com.sudo.androidd20.R
import com.sudo.androidd20.User
import com.sudo.androidd20.fragments.FragmentLogin

class AuthActivity : AppCompatActivity() {

    val listUser: MutableList<User> = mutableListOf(
        User("Duong", "123"),
        User("Minh", "456")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        Navigator
            .of(supportFragmentManager)
            .add(FragmentLogin::class.java)

    }
}