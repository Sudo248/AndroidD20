package com.sudo.androidd20

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sudo.androidd20.databinding.FragmentLoginBinding
import com.sudo.androidd20.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    var accounts:ArrayList<User> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.tvLogIn.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        binding.btnSignUp.setOnClickListener{
            val email = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val confirm_password = binding.etConfirmPassword.text.toString()
            if(!isEmailValid(email)) Toast.makeText(
                context,
                "You didn't enter email in the right way!",
                Toast.LENGTH_LONG
            ).show()
            else if(email.isEmpty()) Toast.makeText(
                context,
                "Email must not be empty!! Try again",
                Toast.LENGTH_LONG
            ).show()
            else if(password.isEmpty()) Toast.makeText(
                context,
                "Password must not be empty!! Try again",
                Toast.LENGTH_LONG
            ).show()
            else if(confirm_password.isEmpty()) Toast.makeText(
                context,
                "Confirm password must not be empty!! Try again",
                Toast.LENGTH_LONG
            )
            else if(confirm_password != password) Toast.makeText(
                context,
                "Confirm password is not correct!",
                Toast.LENGTH_LONG
            ).show()
            else{
                accounts.add(User(email,password))
                Toast.makeText(context,"Successfull",Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLogIn.setOnClickListener {
            val FragmentLogin = LogInFragment()

            val bundle = Bundle()
            FragmentLogin.arguments = bundle
            bundle.putSerializable("acc",accounts)

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.apply {
                replace(R.id.fragment_menu,FragmentLogin)
                commit()
            }
        }


        return binding.root
    }

    fun isEmailValid(email:String) : Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}