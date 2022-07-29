package com.sudo.androidd20.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.sudo.androidd20.R
import com.sudo.androidd20.constants.Constants

/**
 * Why are we still here ?
 * Just to suffer.
 */
class AuthenticateActivity : AppCompatActivity() {
    private val userList = mutableMapOf(
        "admin" to "admin",
        "quang" to "quang",
    )
    private val TAG = "TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authen)

        // Activity receive credential from LoginFragment
        supportFragmentManager.setFragmentResultListener(
            Constants.RECEIVE_LOGIN,
            this@AuthenticateActivity
        ) { _, bundle ->
            val username = bundle.getString(Constants.USERNAME)
            val password = bundle.getString(Constants.PASSWORD)
            if (username != null && password != null) {
                if (userList[username].equals(password))
                    loginSuccess(username, password)
                else
                    loginFailed()
            }
        }

        // Activity receive credential from SignupFragment
        supportFragmentManager.setFragmentResultListener(
            Constants.RECEIVE_SIGNUP,
            this@AuthenticateActivity
        ) { _, bundle ->
            val username = bundle.getString(Constants.USERNAME)
            val password = bundle.getString(Constants.PASSWORD)
            if (username != null && password != null && password.length > 2)
                signupSuccess(username, password)
            else
                signupFailed()
        }
    }

    private fun loginSuccess(username: String, password: String) {
        supportFragmentManager.setFragmentResult(
            Constants.AUTHENTICATE_RESULT,
            bundleOf(
                Constants.FRAGMENT_RESULT to Constants.FRAGMENT_SUCCESS,
                Constants.USERNAME to username,
                Constants.SHORTPASSWORD to password.substring(password.length - 3)
            )
        )
        Log.i(TAG, "Check Login successfully")
    }

    private fun loginFailed() {
        supportFragmentManager.setFragmentResult(
            Constants.AUTHENTICATE_RESULT,
            bundleOf(
                Constants.FRAGMENT_RESULT to Constants.FRAGMENT_FAILED,
            )
        )
        Log.i(TAG, "Check Login failed")
    }

    private fun signupSuccess(username: String, password: String) {
        userList[username] = password

        supportFragmentManager.setFragmentResult(
            Constants.SIGN_UP_RESULT,
            bundleOf(
                Constants.FRAGMENT_RESULT to Constants.FRAGMENT_SUCCESS,
                Constants.USERNAME to username
            )
        )
        Log.i(TAG, "Add credential successfully")
    }

    private fun signupFailed() {
        supportFragmentManager.setFragmentResult(
            Constants.SIGN_UP_RESULT,
            bundleOf(
                Constants.FRAGMENT_RESULT to Constants.FRAGMENT_FAILED,
            )
        )
        Log.i(TAG, "Add credential failed")
    }
}


