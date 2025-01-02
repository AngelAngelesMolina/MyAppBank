package com.example.mibankapp.domain.use_cases.get_user_use_case

import com.example.mibankapp.common.Resource
import com.example.mibankapp.data.remote.dto.request.LoginRequest
import com.example.mibankapp.data.remote.dto.response.UserSecundary
import com.example.mibankapp.data.repository.LoginRepositoryImp
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: LoginRepositoryImp) {

    suspend operator fun invoke(loginRequest: LoginRequest): Resource<UserSecundary> {
        return try {
            val response = repository.getUser(loginRequest)

            if (response != null) {
                Resource.Success(response)
            } else {
                Resource.Error("No se puede iniciar sesión")
            }
        } catch (e: Exception) {
            Resource.Error("No se puede iniciar sesión")
        }
    }

}