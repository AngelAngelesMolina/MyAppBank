package com.example.mibankapp.domain.repository

import com.example.mibankapp.data.remote.dto.LoginRequest
import com.example.mibankapp.data.remote.dto.UserResponse
import com.example.mibankapp.data.remote.dto.UserSecundary

interface LoginRepository {
    suspend fun getUserAlt(user: String, email: String, password: String): UserResponse?
    suspend fun getUser(loginRequest: LoginRequest): UserSecundary?
}