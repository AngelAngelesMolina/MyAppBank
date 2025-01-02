package com.example.mibankapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
)
