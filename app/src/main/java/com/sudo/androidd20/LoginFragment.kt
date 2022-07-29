package com.sudo.androidd20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sudo.androidd20.databinding.FragmentLoginBinding

class LoginFragment : Fragment(){
    private lateinit var fragmentLoginBinding: FragmentLoginBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLoginBinding.tvSignUp.setOnClickListener(View.OnClickListener {
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return fragmentLoginBinding.root
    }
}