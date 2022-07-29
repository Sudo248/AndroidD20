package com.sudo.androidd20

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sudo.androidd20.databinding.FragmentLoginBinding

class LogInFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    var accounts:ArrayList<User> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.tvSignup.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        //getting data
        arguments?.let {
            accounts = it.getSerializable("acc") as ArrayList<User>
        }
        binding.btnLogin.setOnClickListener{
            val email = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
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

            val login_user = User(email,password)
            if(accounts.contains(login_user)){
                val intent = Intent(context,ContentActivity::class.java)

                intent.putExtra("user",login_user.email.toString())
                intent.putExtra("password",login_user.password.toString())
                startActivity(intent)
            } else{
                Toast.makeText(context,"Wrong password or email", Toast.LENGTH_LONG).show()
            }
        }

        binding.tvSignup.setOnClickListener {
            val FragmentSignUP = SignUpFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.apply {
                replace(R.id.fragment_menu,FragmentSignUP)
                commit()
            }
        }

        return binding.root
    }

    private fun isEmailValid(email: String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}