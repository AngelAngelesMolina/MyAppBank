package com.example.mibankapp.domain.repository

import com.example.mibankapp.data.remote.dto.response.ImageResponse

interface ImageRepository {
    suspend fun getImageUser(): ImageResponse?
}