package com.example.mibankapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyBankApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}