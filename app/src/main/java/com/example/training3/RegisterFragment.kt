package com.example.training3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.training3.databinding.FragmentLoginBinding
import com.example.training3.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var users: MutableList<User> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.btnSignUp.setOnClickListener() {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtConfirmPassword.text.toString()
            val user = User(username, password)
            when {
                username.isEmpty() -> Toast.makeText(
                    context,
                    "Fill in your Email or Username",
                    Toast.LENGTH_LONG
                ).show()
                password.isEmpty() -> Toast.makeText(
                    context,
                    "Fill in your Password",
                    Toast.LENGTH_LONG
                ).show()
                confirmPassword.isEmpty() -> Toast.makeText(
                    context,
                    "Fill in your Confirm Password",
                    Toast.LENGTH_LONG
                ).show()
                password != confirmPassword -> Toast.makeText(
                    context,
                    "The confirmation password must be the same as the password",
                    Toast.LENGTH_LONG
                ).show()
                users.contains(user) -> Toast.makeText(
                    context,
                    "This account already exists",
                    Toast.LENGTH_LONG
                ).show()
                else -> {
                    users.add(user)
                    Toast.makeText(context, "You have successfully registered", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        binding.txvLogin.setOnClickListener() {
            val logInFragment = LoginFragment()
            val bundle = Bundle()
            logInFragment.arguments = bundle
            bundle.putSerializable("users", ArrayList(users) )
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.frame_main, logInFragment)
                commit()
            }
        }
        return binding.root

    }
}