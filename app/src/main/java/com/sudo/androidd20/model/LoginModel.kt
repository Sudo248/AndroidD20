package com.sudo.androidd20.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sudo.androidd20.data.User

class LoginModel : ViewModel() {
    private val _users = MutableLiveData<MutableList<User>>(
        mutableListOf(
            User(
                email = "sankatana02@gmail.com",
                userName = "S4ltF1sh",
                password = "123456"
            )
        )
    )
    val users: LiveData<MutableList<User>> get() = _users
    fun add(newUser: User) {
        _users.value?.add(newUser)
    }
}