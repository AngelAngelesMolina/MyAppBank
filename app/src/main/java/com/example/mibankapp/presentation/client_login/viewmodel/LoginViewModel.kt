package com.example.mibankapp.presentation.client_login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mibankapp.common.Resource
import com.example.mibankapp.data.local.SecurePreferences
import com.example.mibankapp.data.remote.dto.request.LoginRequest
import com.example.mibankapp.data.remote.dto.response.UserSecundary
import com.example.mibankapp.domain.use_cases.get_user_use_case.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val securePreferences: SecurePreferences,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _userState = MutableLiveData<Resource<UserSecundary>>()
    val userState: LiveData<Resource<UserSecundary>> get() = _userState

    fun launchLogin(loginRequest: LoginRequest) {
        _userState.value = Resource.Loading
        viewModelScope.launch {
            try {
                val result = getUserUseCase.invoke(loginRequest)
                _userState.value = result
            } catch (e: Exception) {
                _userState.value = Resource.Error("Error al iniciar sesión")
            }
        }
    }

    fun saveUserData(key: String, value: String, callback: () -> Unit) {
        securePreferences.putString(key, value)
        callback()
    }

    fun getUserData(key: String): String? {
        return securePreferences.getString(key)
    }
}