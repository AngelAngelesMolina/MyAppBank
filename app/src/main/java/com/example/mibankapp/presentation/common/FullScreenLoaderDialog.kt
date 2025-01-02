package com.example.mibankapp.presentation.common

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.mibankapp.databinding.FragmentFullScreenLoaderScreenBinding

class FullScreenLoaderDialog : DialogFragment() {


    private lateinit var mBinding: FragmentFullScreenLoaderScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFullScreenLoaderScreenBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.loaderAnimation.apply {
            playAnimation()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), theme).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            window?.apply {
                setBackgroundDrawableResource(android.R.color.transparent)
                setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        }
    }

    companion object {
        fun newInstance(): FullScreenLoaderDialog {
            return FullScreenLoaderDialog()
        }
    }
}