package com.example.training3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.training3.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.let{
            user = it.getSerializable("user") as User
        }
        _binding = FragmentLoginBinding.inflate(inflater,container,false)

        binding.btnLogin.setOnClickListener{
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            val userLogin = User(username, password)
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
                userLogin != user -> Toast.makeText(
                    context,
                    "Wrong Username or Password",
                    Toast.LENGTH_LONG
                ).show()
                else -> {
                    val intent = Intent(context,WriteActivity::class.java)
                    startActivity(intent)
                }
            }

        }

        binding.txvSignUp.setOnClickListener{
            val registerFragment = RegisterFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.frame_main,registerFragment)
                commit()
            }
        }
        return binding.root

    }
}