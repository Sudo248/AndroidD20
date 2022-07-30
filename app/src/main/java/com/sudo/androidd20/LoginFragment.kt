package com.sudo.androidd20

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.sudo.androidd20.databinding.FragmentLoginBinding

class LoginFragment : Fragment(){
    private lateinit var fragmentLoginBinding: FragmentLoginBinding

    private var user: User = User("userName", "password")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        fragmentLoginBinding.tvSignUp.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        arguments?.let {
            user = it.getSerializable("account") as User
        }

        Log.e("USERNAME", "onCreateView1: ${user.userName}", )
        Log.e("USERNAME", "onCreateView1: ${user.password}", )

        setOnClick()





        return fragmentLoginBinding.root
    }

    private fun setOnClick() {
        fragmentLoginBinding.apply {
            tvSignUp.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragmentState, SignUpFragment()).commit()
            }
            btnLogin.setOnClickListener {
                login()
            }
        }
    }

    private fun login() {
        fragmentLoginBinding.apply {
            Log.e("ngu", "userName: ${user.userName}", )
            Log.e("ngu", "password: ${user.password}", )

            if(user.userName == edtUserName.text.toString() && user.password == edtPassword.text.toString()) {
                val intent = Intent(context, Info::class.java)
                val bundle: Bundle = Bundle()
                bundle.putString("userName", edtUserName.text.toString())
                bundle.putString("password", edtPassword.text.toString())
                intent.putExtra("user", bundle)
                startActivity(intent)
            } else {
                Log.e("ngu", "login: ngu", )
            }
        }
    }
}
