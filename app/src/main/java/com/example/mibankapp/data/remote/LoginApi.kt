package com.example.mibankapp.data.remote

import com.example.mibankapp.data.remote.dto.request.LoginRequest
import com.example.mibankapp.data.remote.dto.response.UserSecundary
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("api/login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): Response<UserSecundary>
}