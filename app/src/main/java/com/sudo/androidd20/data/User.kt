package com.sudo.androidd20.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo var email: String,
    @ColumnInfo var userName: String,
    @ColumnInfo var password: String,
    @ColumnInfo val name: String = "Lê Quang Dũng",
    @ColumnInfo var age: Int = 19,
) : Serializable