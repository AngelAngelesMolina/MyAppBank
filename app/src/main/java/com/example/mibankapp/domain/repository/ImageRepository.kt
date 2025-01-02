package com.example.mibankapp.domain.repository

import com.example.mibankapp.data.remote.dto.ImageResponse

interface ImageRepository {
    suspend fun getImageUser(): ImageResponse?
}