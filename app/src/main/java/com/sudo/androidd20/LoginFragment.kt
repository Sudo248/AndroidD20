package com.sudo.androidd20

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.fragment.app.FragmentTransaction
import com.sudo.androidd20.databinding.ActivityMainBinding.inflate
import com.sudo.androidd20.databinding.FragmentLoginBinding
import com.sudo.androidd20.databinding.FragmentLoginBinding.inflate
import com.sudo.androidd20.databinding.FragmentSignUpBinding
import com.sudo.androidd20.databinding.FragmentSignUpBinding.inflate

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    var accounts: ArrayList<User> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.tvSignUp.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        //getting data
        arguments?.let {
            accounts = it.getSerializable("acc") as ArrayList<User>
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (!isEmailValid(email)) Toast.makeText(
                context,
                "You did not enter email in the right way!",
                Toast.LENGTH_LONG
            ).show()
            else if (email.isEmpty()) Toast.makeText(
                context,
                "Email must not be empty",
                Toast.LENGTH_SHORT
            ).show()
            else if (password.isEmpty()) Toast.makeText(
                context,
                "Password must not be empty",
                Toast.LENGTH_LONG
            ).show()
            val login_user = User(email, password)
            if (accounts.contains(login_user)) {
                val intent = Intent(context, MainActivity2::class.java)
                //passing infor to the other activity
                intent.putExtra("name", login_user.email.toString())
                intent.putExtra("password", login_user.password.toString())
                startActivity(intent)
            } else {
                Toast.makeText(context, "Incorrect password or account", Toast.LENGTH_SHORT).show()
            }

        }

        binding.tvSignUp.setOnClickListener {
            val FragmentSignUp = SignUpFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.apply {
                replace(R.id.fragment_menu, FragmentSignUp)
                commit()
            }
        }





        return binding.root
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}