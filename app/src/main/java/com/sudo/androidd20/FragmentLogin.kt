package com.sudo.androidd20

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sudo.androidd20.databinding.LoginFragmentBinding
import org.w3c.dom.Text

class FragmentLogin : Fragment() {
    private var callBackFragment: CallBackFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewBinding = LoginFragmentBinding.inflate(inflater, container, false)
        viewBinding.btnSignIn.setOnClickListener {
            if (viewBinding.etEmail.text.toString() == "") {
                Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
            } else if (viewBinding.etPassword.text.toString() == "")
                Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show()
            else if (viewBinding.etEmail.text.toString() != Data.userName || viewBinding.etPassword.text.toString() != Data.password) {
                Toast.makeText(
                    context,
                    "Wrong email or password",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
            }
        }

        viewBinding.btnSignUp.setOnClickListener {
            Toast.makeText(context, "Sign Up", Toast.LENGTH_SHORT).show()
            callBackFragment?.changeFragment(FragmentSignUp())
        }

        return viewBinding.root
    }

    @JvmName("setCallBackFragment1")
    fun setCallBackFragment(callBackFragment: CallBackFragment) {
        this.callBackFragment = callBackFragment
    }

}