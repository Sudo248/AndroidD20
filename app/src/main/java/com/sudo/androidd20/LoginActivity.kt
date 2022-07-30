package com.sudo.androidd20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sudo.androidd20.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val loginFragment: LoginFragment = LoginFragment()
    private val signUpFragment: SignUpFragment = SignUpFragment()
    private lateinit var activityMainBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.fragmentState, loginFragment).commit()
        }

    }
}

