package com.example.mibankapp.presentation.client_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mibankapp.common.Resource
import com.example.mibankapp.data.local.SecurePreferences
import com.example.mibankapp.data.remote.dto.ImageResponse
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
    // Estado para la imagen
    private val _imageState = MutableLiveData<Resource<ImageResponse>>()
    val imageState: LiveData<Resource<ImageResponse>> get() = _imageState

    // Método para cargar la imagen desde el caso de uso
    fun loadImage() {
        // Primero, marcamos la operación como "Loading" para que la UI pueda mostrar un estado de carga
        _imageState.value = Resource.Loading

        // Realizamos la operación en un coroutine (asíncrona)
        viewModelScope.launch {
            try {
                // Llamamos al caso de uso para obtener la imagen
                val result = getUserImageUseCase.invoke()

                // Según el resultado, actualizamos el estado
                _imageState.value = result
            } catch (e: Exception) {
                // Si ocurre algún error, actualizamos el estado con un mensaje de error
                _imageState.value = Resource.Error("Error al obtener la imagen")
            }
        }
    }
    fun removeUserData(key: String, callback: () -> Unit) {
        securePreferences.remove(key)
        callback()  // Llamamos al callback después de eliminar
    }
}