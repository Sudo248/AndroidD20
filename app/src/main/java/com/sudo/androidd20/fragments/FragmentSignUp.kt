package com.sudo.androidd20.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sudo.androidd20.Navigator
import com.sudo.androidd20.R
import com.sudo.androidd20.activities.AuthActivity
import com.sudo.androidd20.databinding.FragmentSignUpBinding


class FragmentSignUp : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.txtToLogin.setOnClickListener {
//            (requireActivity() as AuthActivity).pop()
            Navigator.of(parentFragmentManager).back()
        }
        return binding.root
    }

    private fun setOnClick() {

    }

}