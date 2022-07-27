package com.sudo.androidd20.fragment

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener
import com.sudo.androidd20.LoginActivity
import com.sudo.androidd20.MainActivity
import com.sudo.androidd20.R
import com.sudo.androidd20.databinding.FragmentLoginBinding

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        setFragmentResultListener("result_to_fragment_login") { _, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.get("userName")
            binding.edtEnterEmail.setText(result.toString())
            // Do something with the result
        }

        binding.tvBtnSignUp.setOnClickListener {
            parentFragmentManager.apply {
                beginTransaction()
                    .replace(R.id.fragment_container, SignUpFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.tvBntLogIn.setOnClickListener {
            val enteredEmail = binding.edtEnterEmail.text.toString()
            val enteredPassword = binding.edtEnterPassword.text.toString()

            if (enteredEmail != "" && enteredPassword != "") {
                for (it in LoginActivity.listUser) {
                    try {
                        if ((it.userName == enteredEmail || it.email == enteredEmail) && it.password == enteredPassword) {
                            val intent = Intent(context, MainActivity::class.java)

                            val bundle = Bundle()
                            bundle.putSerializable("userData", it)
                            intent.putExtras(bundle)
                            startActivity(intent)

                            activity?.finish()
                        }
                    } catch (e: Exception) {
                        throw e
                    }
                }
            }


        }

        return binding.root
    }
}