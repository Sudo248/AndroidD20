package com.sudo.androidd20

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sudo.androidd20.databinding.LoginFragmentBinding

class FragmentLogin : Fragment() {

    private var callBackFragment: ChangeScreenListener? = null
    private lateinit var binding: LoginFragmentBinding
    private var dataUser: MutableList<User> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)

        binding.btnSignIn.setOnClickListener {
            if (binding.etEmail.text.toString() == "") {
                Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
            } else if (binding.etPassword.text.toString() == "")
                Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show()
            else if (dataUser.contains(
                    User(
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()
                    )
                )
            ) {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("email", binding.etEmail.text.toString())
                intent.putExtra("password", binding.etPassword.text.toString())
                startActivity(intent)
//                callBackFragment?.changeActivity(SecondActivity())
            } else {
                Toast.makeText(
                    context,
                    "Wrong email or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvSignUp.setOnClickListener {
            Toast.makeText(context, "Sign Up", Toast.LENGTH_SHORT).show()
            callBackFragment?.onChangeFragment(FragmentSignUp())
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        parentFragmentManager.setFragmentResultListener(
            "request_login",
            this
        ) { _, bundle ->
            val user = bundle.getSerializable("user") as User
            binding.etEmail.setText(user.userName)
            binding.etPassword.setText(user.userPassword)
            dataUser.add(user)
        }
    }

    @JvmName("setCallBackFragment1")
    fun setCallBackFragment(callBackFragment: ChangeScreenListener) {
        this.callBackFragment = callBackFragment
    }

}