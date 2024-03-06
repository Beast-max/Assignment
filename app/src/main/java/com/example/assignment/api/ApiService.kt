package com.example.assignment.api

import com.example.assignment.models.LoginRequest
import com.example.assignment.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}