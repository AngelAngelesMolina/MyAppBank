package com.example.mibankapp.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SecurePreferences @Inject constructor(
    @ApplicationContext context: Context
) {

    private val sharedPreferences: SharedPreferences =
        context.applicationContext.getSharedPreferences("secure_prefs", Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

}