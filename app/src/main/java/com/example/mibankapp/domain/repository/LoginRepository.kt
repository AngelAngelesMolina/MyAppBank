package com.example.mibankapp.domain.repository

import com.example.mibankapp.data.remote.dto.request.LoginRequest
import com.example.mibankapp.data.remote.dto.response.UserResponse
import com.example.mibankapp.data.remote.dto.response.UserSecundary

interface LoginRepository {
    suspend fun getUserAlt(user: String, email: String, password: String): UserResponse?
    suspend fun getUser(loginRequest: LoginRequest): UserSecundary?
}