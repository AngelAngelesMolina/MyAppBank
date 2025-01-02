package com.example.mibankapp.data.remote

import com.example.mibankapp.data.remote.dto.ImageResponse
import com.example.mibankapp.data.remote.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ImageApi {
    @GET("breeds/image/random")
    suspend fun getImageUser(): Response<ImageResponse>
}