package com.sudo.androidd20

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class login_frm : Fragment(R.layout.frm_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btlogin = getView()?.findViewById<Button>(R.id.btlogin)
        btlogin?.setOnClickListener(){
            val intent = Intent(context, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}