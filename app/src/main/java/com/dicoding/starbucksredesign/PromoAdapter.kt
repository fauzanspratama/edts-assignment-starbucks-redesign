package com.dicoding.starbucksredesign

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.starbucksredesign.databinding.ItemPromoBinding

class PromoAdapter(private val promoImages: List<Int>) :
    RecyclerView.Adapter<PromoAdapter.PromoViewHolder>() {

    inner class PromoViewHolder(private val binding: ItemPromoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageRes: Int) {
            binding.ivPromo.setImageResource(imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val binding = ItemPromoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PromoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.bind(promoImages[position])
    }

    override fun getItemCount(): Int = promoImages.size
}
