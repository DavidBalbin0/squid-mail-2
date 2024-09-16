package com.david.squid_mail.service

import com.david.squid_mail.model.LoginResponse
import com.david.squid_mail.model.RegisterResponse
import com.david.squid_mail.model.UserLogin
import com.david.squid_mail.model.UserRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("/api/Auth/register")
    fun registerUser(@Body userRegister: UserRegister): Call<RegisterResponse>

    @POST("/api/Auth/login")
    fun loginUser(@Body userRegister: UserLogin): Call<LoginResponse>
}