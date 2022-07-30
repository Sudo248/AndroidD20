package com.sudo.androidd20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sudo.androidd20.databinding.SignupFragmentBinding

class FragmentSignUp : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewBinding = SignupFragmentBinding.inflate(inflater, container, false)

        viewBinding.btnSignUp.setOnClickListener {
            if (viewBinding.etEmail.text.toString() == "") {
                Toast.makeText(context, "Email is empty", Toast.LENGTH_SHORT).show()
            } else if (viewBinding.etPassword.text.toString() == "") {
                Toast.makeText(context, "Password is empty", Toast.LENGTH_SHORT).show()
            } else if (viewBinding.etConfirmPassword.text.toString() == "") {
                Toast.makeText(context, "Confirm password is empty", Toast.LENGTH_SHORT).show()
            } else if (viewBinding.etPassword.text.toString() == viewBinding.etConfirmPassword.text.toString()) {
                Toast.makeText(context, "Signup success", Toast.LENGTH_SHORT).show()

                val result = Bundle()
                result.putSerializable("user",User(viewBinding.etEmail.text.toString(),viewBinding.etPassword.text.toString()))
                parentFragmentManager.setFragmentResult("request_login", result)
                parentFragmentManager.popBackStack()
                //callBackFragment?.onChangeFragment(FragmentLogin())

                Toast.makeText(context, "Sign In", Toast.LENGTH_SHORT).show()
                // fragmentManager?.popBackStack()
            } else {
                Toast.makeText(
                    context,
                    "Password and confirm password are not match",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewBinding.tvSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
            Toast.makeText(context, "Sign In", Toast.LENGTH_SHORT).show()
            // fragmentManager?.popBackStack()
        }
        return viewBinding.root
    }

}