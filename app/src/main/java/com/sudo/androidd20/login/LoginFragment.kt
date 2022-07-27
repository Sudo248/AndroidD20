package com.sudo.androidd20.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.snackbar.Snackbar
import com.sudo.androidd20.MainActivity
import com.sudo.androidd20.R
import com.sudo.androidd20.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        binding.tvSignup.setOnClickListener {
            goToSignUpFragment()
        }
        binding.buttonLogin.setOnClickListener {
            loginPushed()
        }
        return binding.root
    }

    private fun goToSignUpFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SignupFragment>(R.id.fragment_container_view)
        }
    }

    private fun loginPushed() {
        val check = true
        // TODO: Implement Authenticating User

        if (check) {
            Snackbar.make(binding.root, "Login Pushed", Snackbar.LENGTH_SHORT)
            startActivity(Intent(context, MainActivity::class.java))
        }
    }

}