package com.example.mibankapp.presentation.client_detail.support

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mibankapp.R
import com.example.mibankapp.databinding.FragmentHomeBinding
import com.example.mibankapp.databinding.FragmentSupportBinding

class SupportFragment : Fragment() {
    private lateinit var  mBinding : FragmentSupportBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSupportBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        mBinding.btnMail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:angel.angeles.molina@gmail.com") // Dirección de correo a la que se enviará el mensaje
            }
            requireActivity().let {
                if (emailIntent.resolveActivity(it.packageManager) != null) {
                    startActivity(emailIntent)
                } else {
                    Toast.makeText(it, "No hay aplicaciones de correo disponibles", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SupportFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}