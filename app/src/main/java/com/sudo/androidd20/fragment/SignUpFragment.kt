package com.sudo.androidd20.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.sudo.androidd20.LoginActivity
import com.sudo.androidd20.R
import com.sudo.androidd20.data.User
import com.sudo.androidd20.databinding.FragmentSignUpBinding
import com.sudo.androidd20.model.LoginModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val loginModel: LoginModel by activityViewModels()

    interface UpdateUsersListListener {
        fun addUser(user: User)
    }

    private lateinit var updateUsersListListener: UpdateUsersListListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        updateUsersListListener = context as UpdateUsersListListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        setOnCLick()

        return binding.root
    }

    private fun setOnCLick() {
        binding.apply {
            tvBtnLogin.setOnClickListener {
                changeToLoginFragment()
            }

            btnSignup.setOnClickListener {
                signup()
            }
        }
    }

    private fun changeToLoginFragment() {
        parentFragmentManager.apply {
            popBackStack()
        }
    }

    private fun signup() {
        val enteredUserName = binding.edtEmailOrUsername.text.toString().trim()
        val enteredPassword = binding.edtEnteredPassword.text.toString()
        if (enteredPassword == "") {
            return
        }
        //Nếu dữ liệu đăng ký thỏa mãn:
        if (enteredPassword != binding.edtConfirmedPassword.text.toString() && enteredPassword != "") {
            Toast.makeText(context, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show()
            resetPasswordEditText()
        } else {
            //thực hiện việc thêm người dùng mới vào database (tạm hiểu là thế)
//            if (enteredUserName.contains("@gmail.com"))
//                loginModel.add(
//                    User(
//                        email = enteredUserName,
//                        userName = "None",
//                        password = enteredPassword
//                    )
//                )
//            else {
//                loginModel.add(
//                    User(
//                        email = "None",
//                        userName = enteredUserName,
//                        password = enteredPassword
//                    )
//                )
//            }

            if (enteredUserName.contains("@gmail.com"))
                updateUsersListListener.addUser(
                    User(
                        email = enteredUserName,
                        userName = "None",
                        password = enteredPassword
                    )
                )
            else {
                updateUsersListListener.addUser(
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
                //set dữ liệu kèm theo requesKey để trả dữ liệu về cho LoginFragment - đang lắng nghe
                setFragmentResult("result_to_fragment_login", bundle)
                //set dữ liệu trả về xong thì mới popBackStack()
                popBackStack()
            }

            Toast.makeText(context, "Đăng ký thành công, mời đăng nhập!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun resetPasswordEditText() {
        binding.apply {
            edtEnteredPassword.setText("")
            edtConfirmedPassword.setText("")
            edtEnteredPassword.requestFocus()
        }
    }
}