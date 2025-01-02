package com.example.mibankapp.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

fun <T : Activity> Context.navigateTo(destination: Class<T>, finishCurrent: Boolean = false) {
    val intent = Intent(this, destination)
    startActivity(intent)
    if (finishCurrent && this is Activity) {
        finish()
    }
}
