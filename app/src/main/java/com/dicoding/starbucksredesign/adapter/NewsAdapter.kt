package com.dicoding.starbucksredesign.adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.starbucksredesign.data.NewsItem
import com.dicoding.starbucksredesign.databinding.ItemNewsBinding
import com.dicoding.starbucksredesign.ui.NewsDetailActivity
import android.util.Pair as UtilPair

class NewsAdapter(
    private val activity: Activity,
    private val newsList: List<NewsItem>
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        val binding = holder.binding

        // Ensure transition names are correctly set
        val imageTransitionName = "news_image_${newsItem.id}"

        binding.ivNewsImage.transitionName = imageTransitionName

        // Bind data
        binding.tvNewsTitle.text = newsItem.title
        binding.tvNewsCategory.text = newsItem.category
        binding.tvNewsDate.text = newsItem.date
        binding.tvNewsReadTime.text = "${newsItem.readTime} read time"
        binding.ivNewsImage.setImageResource(newsItem.imageResId)

        // Click listener for transition
        binding.root.setOnClickListener {
            val intent = Intent(activity, NewsDetailActivity::class.java).apply {
                putExtra("news_id", newsItem.id)
                putExtra("news_title", newsItem.title)
                putExtra("news_category", newsItem.category)
                putExtra("news_date", newsItem.date)
                putExtra("news_read_time", newsItem.readTime)
                putExtra("news_image", newsItem.imageResId)
            }

            val options = ActivityOptions.makeSceneTransitionAnimation(
                activity,
                UtilPair.create(binding.ivNewsImage, imageTransitionName)
            )

            activity.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int = newsList.size
}

