package com.dicoding.starbucksredesign.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.starbucksredesign.databinding.ItemHeroBannerBinding

class HeroBannerAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<HeroBannerAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(private val binding: ItemHeroBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageRes: Int) {
            binding.ivSliderImage.setImageResource(imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = ItemHeroBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}
