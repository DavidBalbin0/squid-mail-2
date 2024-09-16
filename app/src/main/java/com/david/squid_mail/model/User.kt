package com.david.squid_mail.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class User(
    val id: Long = 0,
    val name: String,
    val email: String
)