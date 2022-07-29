package com.sudo.androidd20

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        val sign_up_or_in = findViewById<Button>(R.id.sign_up_or_in)
        val have_account_or_not = findViewById<TextView>(R.id.have_account_or_not)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flfragment, firstFragment)
            commit()
        }
        sign_up_or_in.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                if(sign_up_or_in.text=="Sign up"){
                    sign_up_or_in.text="Log in"
                    have_account_or_not.text="Already, have an account?"
                    replace(R.id.flfragment, secondFragment)
                    addToBackStack(null)
                    commit()
                }
                else{
                    sign_up_or_in.text="Sign up"
                    have_account_or_not.text="Don't have account?"
                    replace(R.id.flfragment, firstFragment)
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }
}