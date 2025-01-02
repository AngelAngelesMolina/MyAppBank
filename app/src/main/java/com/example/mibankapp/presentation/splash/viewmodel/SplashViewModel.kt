package com.example.mibankapp.presentation.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mibankapp.data.local.SecurePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val securePreferences: SecurePreferences,
    ) : ViewModel() {
    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun checkUserLoginStatus(keyValue :String) {
        val valueSp = securePreferences.getString(keyValue, "")
        if (valueSp != null) {
            _isLoggedIn.value = valueSp.isNotEmpty() && valueSp.isNotBlank()
        }
    }
}