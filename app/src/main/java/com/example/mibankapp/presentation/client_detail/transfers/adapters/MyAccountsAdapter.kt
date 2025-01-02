package com.example.mibankapp.presentation.client_detail.transfers.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mibankapp.databinding.ItemAccountBinding
import com.example.mibankapp.domain.model.ItemAccount

class MyAccountsAdapter(private val items: List<ItemAccount>) :
    RecyclerView.Adapter<MyAccountsAdapter.MyViewHolder>() {
    inner class MyViewHolder(private val binding: ItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemAccount) {
            binding.tvAccountName.text = item.accountName
            binding.tvAvailableAmount.text = item.cash
            binding.tvCardNumber.text = item.cardNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemAccountBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}