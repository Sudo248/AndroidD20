package com.sudo.androidd20

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sudo.androidd20.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    val accounts: ArrayList<User> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.btnSignup.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
                if(email.isEmpty()) Toast.makeText(context, "Email must not be empty", Toast.LENGTH_SHORT).show()
                else if(password.isEmpty()) Toast.makeText(context,"Password must not be empty",Toast.LENGTH_LONG).show()
                else{
                    accounts.add(User(email,password))
                    Toast.makeText(context, "${email} has just been created", Toast.LENGTH_SHORT).show()
                }

        }
        binding.tvLogin.setOnClickListener {
            val FragmentLogin = LoginFragment()
            //passing data
            val bundle = Bundle()
            FragmentLogin.arguments=bundle
            bundle.putSerializable("acc",accounts)
            //transition
            val transaction= requireActivity().supportFragmentManager.beginTransaction()
            transaction.apply{
                replace(R.id.fragment_menu,FragmentLogin)
                commit()
            }
        }
        return binding.root
    }
}