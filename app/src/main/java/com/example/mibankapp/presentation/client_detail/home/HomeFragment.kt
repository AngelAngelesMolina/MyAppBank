package com.example.mibankapp.presentation.client_detail.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.mibankapp.R
import com.example.mibankapp.common.LoaderUtils
import com.example.mibankapp.common.Resource
import com.example.mibankapp.databinding.FragmentHomeBinding
import com.example.mibankapp.presentation.client_detail.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        homeViewModel.loadImage()
    }

    private fun setupObserver() {
        homeViewModel.imageState.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    LoaderUtils.showLoader(requireActivity().supportFragmentManager, true)
                }

                is Resource.Success -> {
                    LoaderUtils.showLoader(requireActivity().supportFragmentManager, false)
                    Glide.with(requireContext()).load(resource.data.message).into(mBinding.ivUser)
                }

                is Resource.Error -> {
                    mBinding.ivUser.visibility = View.GONE
                    LoaderUtils.showLoader(requireActivity().supportFragmentManager, false)
                    LoaderUtils.showAlert(
                        mContext = requireContext(),
                        title = getString(R.string.title_generic_dialog),
                        msg = resource.message
                    ) {}
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {

            }
    }
}