package com.example.mibankapp.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
)
