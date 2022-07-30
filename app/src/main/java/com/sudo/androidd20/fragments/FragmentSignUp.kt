package com.sudo.androidd20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.sudo.androidd20.Constants
import com.sudo.androidd20.Navigator
import com.sudo.androidd20.R
import com.sudo.androidd20.User
import com.sudo.androidd20.activities.AuthActivity
import com.sudo.androidd20.databinding.FragmentSignUpBinding


class FragmentSignUp : Fragment() {

    companion object {
        const val FRAGMENT_SIGNUP_LISTENER = "FRAGMENT_SIGNUP_LISTENER"
    }

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        setOnClickListener()
        return binding.root
    }

    private fun setOnClickListener() {
        with(binding) {
            txtToLogin.setOnClickListener {
                Navigator.of(parentFragmentManager).navigate(FragmentLogin::class.java)
            }
            btnSignup.setOnClickListener {
                onSignUpClickListener()
            }
        }
    }

    private fun onSignUpClickListener() {
        if (isSamePassWord()) {
            val user = getInputUser()
            if (isInvalidUser(user)) {
                addUserAndBackLogin(user)
            } else {
                showToast("Invalid User")
            }
        } else {
            showToast("Wrong password")
        }
    }

    private fun getInputUser(): User {
        val email = binding.tilEmail.editText?.text.toString()
        val password = binding.tilPassword.editText?.text.toString()
        return User(email, password)
    }

    private fun isSamePassWord(): Boolean {
        val password = binding.tilPassword.editText?.text.toString()
        val confirmPassword = binding.tilConfirmPassword.editText?.text.toString()
        return password == confirmPassword
    }

    private fun isInvalidUser(user: User): Boolean {
        return !(activity as AuthActivity).listUser.contains(user)
    }

    private fun addUserAndBackLogin(user: User) {
        (activity as AuthActivity).listUser.add(user)
        setFragmentResult(FragmentLogin.FRAGMENT_LOGIN_LISTENER, bundleOf(Constants.USER to user))
        Navigator.of(parentFragmentManager).back()
    }

    private fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}