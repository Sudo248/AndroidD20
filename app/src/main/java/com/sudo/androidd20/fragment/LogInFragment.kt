package com.sudo.androidd20.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sudo.androidd20.LoginActivity
import com.sudo.androidd20.MainActivity
import com.sudo.androidd20.R
import com.sudo.androidd20.data.User
import com.sudo.androidd20.databinding.FragmentLoginBinding
import com.sudo.androidd20.model.LoginModel

class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginModel: LoginModel by activityViewModels()
    private lateinit var listUser: MutableList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        //tạo setFragmentResultListener để lắng nghe kết quả trả về (dưới dạng Bundle)
        setFragmentResultListener("result_to_fragment_login") { _, bundle ->
            //Sau khi lấy được dữ liệu trả về sẽ xử lý và gán username cho phần ô nhập
            val result = bundle.get("userName")
            binding.edtEnterEmail.setText(result.toString())
        }

        setOnClick()

        return binding.root
    }

    private fun setOnClick() {
        binding.apply {
            tvSignUp.setOnClickListener {
                changeToSignUpFragment()
            }

            btnLogin.setOnClickListener {
                login()
            }
        }
    }

    private fun changeToSignUpFragment() {
        parentFragmentManager.apply {
            beginTransaction()
                .replace(R.id.fragment_container, SignUpFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun login() {
        loginModel.users.observe(viewLifecycleOwner, Observer<MutableList<User>> {
            listUser = it
            Log.i("LoginModel", "$it")
        })
        val enteredEmail = binding.edtEnterEmail.text.toString()
        val enteredPassword = binding.edtEnterPassword.text.toString()

        if (enteredEmail != "" && enteredPassword != "") {
            for (user in listUser) {
                try {
                    if ((user.userName == enteredEmail || user.email == enteredEmail) && user.password == enteredPassword) {
                        val intent = Intent(context, MainActivity::class.java)
                        val bundle = Bundle()

                        bundle.putSerializable("userData", user)
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
}