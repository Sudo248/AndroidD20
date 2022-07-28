package com.sudo.androidd20.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.sudo.androidd20.MainActivity
import com.sudo.androidd20.R
import com.sudo.androidd20.constants.Constants
import com.sudo.androidd20.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private val TAG = "TAG"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)

        // LoginFragment launches after SignupFragment success
        setFragmentResultListener(Constants.SIGN_UP_TO_LOGIN) { _, bundle ->
            binding.etLoginEmail.setText(bundle.getString(Constants.USERNAME))
        }

        // LoginFragment start MainActivity after authenticated
        setFragmentResultListener(Constants.AUTHENTICATE_RESULT) { _, bundle ->
            if (bundle.getString(Constants.FRAGMENT_RESULT).equals(Constants.FRAGMENT_SUCCESS)) {
                val intent = Intent(context, MainActivity::class.java)
                    .putExtra(Constants.USERNAME, bundle.getString(Constants.USERNAME))
                    .putExtra(Constants.SHORTPASSWORD, bundle.getString(Constants.SHORTPASSWORD))
                startActivity(intent)
            } else
                Toast.makeText(requireContext(), "Can not login", Toast.LENGTH_SHORT).show()
        }
        binding.tvSignup.setOnClickListener {
            goToSignUpFragment()
        }
        binding.buttonLogin.setOnClickListener {
            loginPushed(
                binding.etLoginEmail.text.toString(),
                binding.etLoginPassword.text.toString()
            )
        }

        return binding.root
    }

    private fun goToSignUpFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SignupFragment>(R.id.fragment_container_view)
        }
    }

    private fun loginPushed(username: String, password: String) {
        Log.i(TAG, "loginPushed: Send Login back to MainActivity")
        setFragmentResult(
            Constants.RECEIVE_LOGIN, bundleOf(
                Constants.USERNAME to username,
                Constants.PASSWORD to password
            )
        )
    }

}