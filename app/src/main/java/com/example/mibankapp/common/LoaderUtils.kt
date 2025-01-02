package com.example.mibankapp.common

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.example.mibankapp.presentation.common.FullScreenLoaderDialog

object LoaderUtils {
    private var loaderDialog: FullScreenLoaderDialog? = null

    fun showLoader(fragmentManager: FragmentManager, shouldShow: Boolean) {
        if (shouldShow) {
            if (loaderDialog == null) {
                loaderDialog = FullScreenLoaderDialog.newInstance()
            }
            if (!loaderDialog!!.isAdded) {
                loaderDialog!!.show(fragmentManager, "LoaderDialog")
            }
        } else {
            loaderDialog?.dismiss()
            loaderDialog = null
        }
    }

    fun showAlert(
        mContext: Context,
        title: String,
        isNegativeButton: Boolean = false,
        msg: String,
        onPositiveClick: () -> Unit
    ) {
        val alertDialog = AlertDialog.Builder(mContext)
            .setTitle(title)
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("Aceptar") { dialog, _ ->
                onPositiveClick()
                dialog.dismiss()
            }

        if (isNegativeButton) {
            alertDialog.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
        }

        alertDialog.create().show()
    }
}