package com.david.squid_mail.model

data class LoginResponse(
    val token: String,
    val success: Boolean,
    val message: String
)
