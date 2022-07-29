package com.sudo.androidd20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

open class MainActivity : AppCompatActivity(), CallBack {
    private var fragment: Fragment? = null
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment()
    }

    private fun addFragment() {
        val fragment = FragmentLogin()
        fragment.setCallBackFragment(this)
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.add(
            R.id.frame_container,
            fragment
        )
        fragmentTransaction?.commit()
    }

    private fun replaceFragment(Fragment: Fragment) {
        fragment = Fragment
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.replace(
            R.id.frame_container,
            fragment as FragmentSignUp
        )
        fragmentTransaction?.commit()
    }

    private fun replaceActivity(Activity: Class<*>) {
        val intent = Intent(this, Activity)
        startActivity(intent)
    }

    //    override fun onBackPressed() {
//        if (fragment is FragmentSignUp) {
//            replaceFragment(FragmentLogin())
//        } else {
//            super.onBackPressed()
//        }
//    }
    override fun changeActivity(Activity: AppCompatActivity) {
        replaceActivity(Activity::class.java)
    }

    override fun changeFragment(Fragment: Fragment) {
        replaceFragment(Fragment)
    }

}