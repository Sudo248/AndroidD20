package com.sudo.androidd20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        return fragmentSignUpBinding.root
    }
}