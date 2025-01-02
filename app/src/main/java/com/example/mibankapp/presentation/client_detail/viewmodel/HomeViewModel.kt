package com.example.mibankapp.presentation.client_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mibankapp.common.Resource
import com.example.mibankapp.data.local.SecurePreferences
import com.example.mibankapp.data.remote.dto.response.ImageResponse
import com.example.mibankapp.domain.use_cases.get_user_img_use_case.GetUserImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val securePreferences: SecurePreferences,
    private val getUserImageUseCase: GetUserImageUseCase
) :
    ViewModel() {
    private val _imageState = MutableLiveData<Resource<ImageResponse>>()
    val imageState: LiveData<Resource<ImageResponse>> get() = _imageState

    fun loadImage() {
        _imageState.value = Resource.Loading

        viewModelScope.launch {
            try {
                val result = getUserImageUseCase.invoke()
                _imageState.value = result
            } catch (e: Exception) {
                _imageState.value = Resource.Error("Error al obtener la imagen")
            }
        }
    }
    fun removeUserData(key: String, callback: () -> Unit) {
        securePreferences.remove(key)
        callback()
    }
}