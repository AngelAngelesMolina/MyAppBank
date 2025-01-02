package com.example.mibankapp.domain.use_cases.get_user_img_use_case

import com.example.mibankapp.common.Resource
import com.example.mibankapp.data.remote.dto.response.ImageResponse
import com.example.mibankapp.data.repository.ImageRepositoryImp
import javax.inject.Inject

class GetUserImageUseCase @Inject constructor(private val repository: ImageRepositoryImp)  {
    suspend operator fun invoke(): Resource<ImageResponse> {
        return try {
            val response = repository.getImageUser()

            if (response != null) {
                Resource.Success(response)
            } else {
                Resource.Error("No se pudo obtener la imagen")
            }
        } catch (e: Exception) {
            Resource.Error("Error al obtener la imagen")
        }
    }
}