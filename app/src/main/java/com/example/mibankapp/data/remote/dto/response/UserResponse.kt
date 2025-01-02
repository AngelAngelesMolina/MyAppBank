package com.example.mibankapp.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("age")
    val age: Int,
)
