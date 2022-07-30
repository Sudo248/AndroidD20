package com.sudo.androidd20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.sudo.androidd20.databinding.FragmentLoginBinding
import com.sudo.androidd20.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {
    private lateinit var fragmentSignUpBinding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        fragmentSignUpBinding.apply {
            tvLogin.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragmentState, LoginFragment()).commit()
            }
            btnSignUp.setOnClickListener {
//                childFragmentManager.setFragmentResult("userName", bundleOf(Pair("userName", edtUserName.text.toString())))


                signUp()
            }
        }



        return fragmentSignUpBinding.root



    }

    private fun signUp() {
        fragmentSignUpBinding.apply {
            if(edtPassword.text.toString() == edtConfirmPasword.text.toString()) {
                val user = User(edtUserName.text.toString(), edtPassword.text.toString())
                val loginFragment = LoginFragment()
                val bundle = Bundle()
                bundle.putSerializable("account", user)
                loginFragment.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragmentState, loginFragment).commit()
            }
        }
    }


}