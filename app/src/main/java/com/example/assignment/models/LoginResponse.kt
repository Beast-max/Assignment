package com.example.assignment.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.google.gson.annotations.Expose

@Keep
data class LoginResponse(
    @SerializedName("token")
    @Expose
    var token: String
)