package com.sudo.androidd20

import java.io.Serializable

data class User (
        var userName: String,
        var userPassword: String,
) : Serializable