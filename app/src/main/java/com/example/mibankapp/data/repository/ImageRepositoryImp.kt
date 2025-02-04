package com.example.mibankapp.data.repository

import com.example.mibankapp.data.remote.ImageApi
import com.example.mibankapp.data.remote.dto.response.ImageResponse
import com.example.mibankapp.domain.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImp @Inject constructor(private val api: ImageApi) : ImageRepository {
    override suspend fun getImageUser(): ImageResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getImageUser()
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