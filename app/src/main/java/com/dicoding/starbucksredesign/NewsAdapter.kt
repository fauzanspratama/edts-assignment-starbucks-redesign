package com.dicoding.starbucksredesign

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.starbucksredesign.databinding.ItemNewsBinding

class NewsAdapter(private val newsList: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]

        with(holder.binding) {
            tvNewsCategory.text = newsItem.category
            tvNewsTitle.text = newsItem.title
            tvNewsDate.text = newsItem.date
            tvNewsReadTime.text = newsItem.readTime
            ivNewsImage.setImageResource(newsItem.imageResId)
        }
    }

    override fun getItemCount(): Int = newsList.size
}