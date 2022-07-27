package com.sudo.androidd20.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import com.sudo.androidd20.LoginActivity
import com.sudo.androidd20.R
import com.sudo.androidd20.data.User
import com.sudo.androidd20.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.tvBtnLogin.setOnClickListener {
            parentFragmentManager.apply {
                popBackStack()
            }
        }

        binding.tvBtnSingUp.setOnClickListener {
            val enteredUserName = binding.edtEmailOrUsername.text.toString().trim()
            val enteredPassword = binding.edtEnterPassword.text.toString()
            if (binding.edtEnterPassword.text.toString() != binding.edtConfirmPassword.text.toString()) {
                Toast.makeText(context, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show()
                binding.edtEnterPassword.setText("")
                binding.edtConfirmPassword.setText("")
                binding.edtEnterPassword.requestFocus()
            } else {
                if (enteredUserName.contains("@gmail.com"))
                    LoginActivity.listUser.add(
                        User(
                            email = enteredUserName,
                            userName = "None",
                            password = enteredPassword
                        )
                    )
                else {
                    LoginActivity.listUser.add(
                        User(
                            email = "None",
                            userName = enteredUserName,
                            password = enteredPassword
                        )
                    )
                }

                parentFragmentManager.apply {

                    val bundle = Bundle()
                    bundle.putString("userName", enteredUserName)
                    setFragmentResult("result_to_fragment_login", bundle)
                    popBackStack()
                }

                Toast.makeText(context, "Đăng ký thành công, mời đăng nhập!", Toast.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root
    }
}