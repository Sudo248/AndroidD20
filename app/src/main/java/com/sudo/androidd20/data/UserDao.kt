package com.sudo.androidd20.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(vararg user: User)

    @Query("SELECT * FROM User ")
    fun getAllUsers(): List<User>
}