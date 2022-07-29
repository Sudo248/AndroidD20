package com.sudo.androidd20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sudo.androidd20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val loginFragment: LoginFragment = LoginFragment()
    private val signUpFragment: SignUpFragment = SignUpFragment()
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentState, loginFragment)
            commit()
        }
    }
}