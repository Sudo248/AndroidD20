package com.sudo.androidd20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

open class MainActivity : AppCompatActivity(), ChangeScreenListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
    }

    private fun addFragment() {
        val fragment = FragmentLogin()
        fragment.setCallBackFragment(this)
        supportFragmentManager.beginTransaction().add(
            R.id.frame_container,
            fragment
        ).commit()
    }

    private fun replaceFragment(fragment: Fragment) {

        supportFragmentManager.beginTransaction().addToBackStack(null).replace(
            R.id.frame_container,
            fragment
        ).commit()
    }

    private fun replaceActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    //    override fun onBackPressed() {
//        if (fragment is FragmentSignUp) {
//            replaceFragment(FragmentLogin())
//        } else {
//            super.onBackPressed()
//        }
//    }
    override fun changeActivity(activity: AppCompatActivity) {
        replaceActivity(activity::class.java)
    }

    override fun onChangeFragment(fragment: Fragment) {
        replaceFragment(fragment)
    }
}