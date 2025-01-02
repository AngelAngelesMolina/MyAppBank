package com.example.mibankapp.data.repository

import com.example.mibankapp.data.remote.LoginApi
import com.example.mibankapp.data.remote.dto.LoginRequest
import com.example.mibankapp.data.remote.dto.UserResponse
import com.example.mibankapp.data.remote.dto.UserSecundary
import com.example.mibankapp.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(private val api: LoginApi) : LoginRepository {
    override suspend fun getUserAlt(user: String, email: String, password: String): UserResponse? {
        return UserResponse("null", "null", "null", "null", 0)
    }

    override suspend fun getUser(loginRequest: LoginRequest): UserSecundary? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.postLogin(loginRequest) // Realizamos la llamada a la API
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }

}