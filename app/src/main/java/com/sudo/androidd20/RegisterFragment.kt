package com.sudo.androidd20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sudo.androidd20.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        binding.register.setOnClickListener {

            signup(binding.username.text.toString(),binding.password.text.toString(),binding.confirmedPassword.text.toString())
        }
        binding.tvSignIn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return binding.root
    }

    private fun signup(username: String?, password: String?, confirmedPassword: String?) {
        if (username.isNullOrEmpty()  || password.isNullOrEmpty() || confirmedPassword.isNullOrEmpty() ) {
            Toast.makeText(context,"Please complete all information",Toast.LENGTH_SHORT).show()
        }
        else if(password.compareTo(confirmedPassword)!=0){
            Toast.makeText(context,"Password does not match",Toast.LENGTH_SHORT).show()
        }
        else{
//            (activity as MainActivity).updateUsersList(User(username,password))
            Toast.makeText(context,"Create account success",Toast.LENGTH_SHORT).show()
            parentFragmentManager.apply {
                val bundle = Bundle()
                bundle.putSerializable("userName",User(username,password))
                setFragmentResult("result_to_fragment_login", bundle)
                popBackStack()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}