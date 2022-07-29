package com.sudo.androidd20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.sudo.androidd20.data.User
import com.sudo.androidd20.fragment.LogInFragment

class LoginActivity : AppCompatActivity()/*, LogInFragment.GetUsersListListener,
    SignUpFragment.UpdateUsersListListener*/ {
    //private val loginModel: LoginModel by viewModels()
    private lateinit var listUser: MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        linkToLoginFragment()
        loadUsersData()
    }

    private fun linkToLoginFragment() {
        supportFragmentManager.commit {
            add<LogInFragment>(R.id.fragment_container)
        }
    }

    //mục đích chính là load dữ liệu người dùng từ database nên em đặt tên thế này
    private fun loadUsersData() {
//        loginModel.add(
//            User(
//                email = "admin",
//                userName = "admin",
//                password = "admin"
//            )
//        )

        listUser = mutableListOf(
            User(
                email = "admin",
                userName = "admin",
                password = "admin"
            )
        )

        Log.i("LoginModel", "add S4ltF1sh!")
    }

//    override fun getUsersList(): MutableList<User> {
//        return listUser
//    }
//
//    override fun addUser(user: User) {
//        listUser.add(user)
//    }

    public fun getUsersList(): MutableList<User> {
        return listUser
    }

    public fun updateUsersList(user: User) {
        listUser.add(user)
    }
}