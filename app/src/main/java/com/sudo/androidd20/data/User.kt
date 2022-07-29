package com.sudo.androidd20.data

import java.io.Serializable

data class User(
    var email: String,
    var userName: String,
    var password: String,
    val name: String = "Lê Quang Dũng",
    var age: Int = 19,
) : Serializable