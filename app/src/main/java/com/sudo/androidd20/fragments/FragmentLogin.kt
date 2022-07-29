package com.sudo.androidd20.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import com.sudo.androidd20.Constants
import com.sudo.androidd20.Navigator
import com.sudo.androidd20.User
import com.sudo.androidd20.activities.AuthActivity
import com.sudo.androidd20.activities.MainActivity
import com.sudo.androidd20.databinding.FragmentLoginBinding

class FragmentLogin : Fragment() {

    companion object {
        const val FRAGMENT_LOGIN_LISTENER = "FRAGMENT_LOGIN_LISTENER"
    }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setOnClickListener()
        setFragmentListener()
        return binding.root
    }

    private fun setOnClickListener() {
        with(binding) {
            txtToSignup.setOnClickListener {
                Navigator.of(parentFragmentManager).navigate(FragmentSignUp::class.java)
            }
            btnLogin.setOnClickListener {
                val user = getInputUser()
                if (isAcceptUser(user)) {
                    startMainActivityWith(user)
                } else {
                    showToast("User invalid or wrong password")
                }
            }
        }
    }

    private fun setFragmentListener() {
        setFragmentResultListener(FRAGMENT_LOGIN_LISTENER) { _, bundle ->
            val user = bundle.getSerializable(Constants.USER) as User
            binding.tilEmail.editText?.setText(user.email)
            binding.tilPassword.editText?.requestFocus()
        }
    }

    private fun getInputUser(): User {
        val email = binding.tilEmail.editText?.text.toString()
        val password = binding.tilPassword.editText?.text.toString()
        return User(email, password)
    }

    private fun isAcceptUser(user: User): Boolean {
        return (activity as AuthActivity).listUser.contains(user)
    }

    private fun startMainActivityWith(user: User) {
        val intent = Intent(activity, MainActivity::class.java).apply {
            putExtra(Constants.USER, user)
        }
        startActivity(intent)
        activity?.finish()
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}