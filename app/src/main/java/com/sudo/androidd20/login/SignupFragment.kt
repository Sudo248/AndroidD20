package com.sudo.androidd20.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.sudo.androidd20.R
import com.sudo.androidd20.constants.Constants
import com.sudo.androidd20.databinding.FragmentSignupBinding

class SignupFragment : Fragment(R.layout.fragment_signup) {
    private lateinit var binding: FragmentSignupBinding
    private val TAG = "TAG"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)

        // SignupFragment receive result from MainActivity
        setFragmentResultListener(Constants.SIGN_UP_RESULT) { _, bundle ->
            if (bundle.getString(Constants.FRAGMENT_RESULT).equals(Constants.FRAGMENT_SUCCESS))
                goToLoginFragment(bundle)
            else
                Toast.makeText(context, "Signup failed", Toast.LENGTH_SHORT).show()
        }
        binding.tvSignup.setOnClickListener {
            goToLoginFragment()
        }
        binding.buttonSignUp.setOnClickListener {
            signUpPushed(
                binding.etLoginEmail.text.toString(),
                binding.etLoginPassword.text.toString(),
                binding.etLoginCfPassword.text.toString(),
            )
        }
        return binding.root
    }

    private fun goToLoginFragment(bundle: Bundle? = null) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            if (bundle != null) {
                setFragmentResult(Constants.SIGN_UP_TO_LOGIN, bundle)
            }
            replace<LoginFragment>(R.id.fragment_container_view)
        }
    }

    private fun signUpPushed(username: String, password: String, confirmPassword: String) {
        if (password.equals(confirmPassword)) {
            Log.i(TAG, "signupPusher: Send signup to MainActivity")
            setFragmentResult(
                Constants.RECEIVE_SIGNUP, bundleOf(
                    Constants.USERNAME to username,
                    Constants.PASSWORD to password
                )
            )
        } else
            Toast.makeText(context, "Confirm password does not match", Toast.LENGTH_SHORT).show()
    }
}