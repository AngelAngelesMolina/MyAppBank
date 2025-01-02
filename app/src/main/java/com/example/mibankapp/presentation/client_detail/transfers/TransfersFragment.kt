package com.example.mibankapp.presentation.client_detail.transfers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mibankapp.R
import com.example.mibankapp.databinding.FragmentHomeBinding
import com.example.mibankapp.databinding.FragmentTransfersBinding
import com.example.mibankapp.domain.model.ItemAccount
import com.example.mibankapp.presentation.client_detail.transfers.adapters.MyAccountsAdapter

class TransfersFragment : Fragment() {

    private lateinit var mBinding: FragmentTransfersBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentTransfersBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val accounts = listOf(
            ItemAccount(
                accountName = "Cuenta Corriente",
                cash = "$10,500.00",
                cardNumber = "**** **** **** 1234"
            ),
            ItemAccount(
                accountName = "Cuenta de Ahorros",
                cash = "$25,300.50",
                cardNumber = "**** **** **** 5678"
            ),
            ItemAccount(
                accountName = "Fondo de Inversiones",
                cash = "$100,000.00",
                cardNumber = "**** **** **** 9876"
            )
        )
        setupRv(accounts)

    }
    private fun setupRv(accounts : List<ItemAccount>){
        val adapter = MyAccountsAdapter(accounts)
        mBinding.rvAccounts.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvAccounts.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TransfersFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}