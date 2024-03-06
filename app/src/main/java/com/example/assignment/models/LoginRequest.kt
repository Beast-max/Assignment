package com.example.assignment.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class LoginRequest(
    @SerializedName("email")
    @Expose
    var email: String,
    @SerializedName("password")
    @Expose
    var password: String
)