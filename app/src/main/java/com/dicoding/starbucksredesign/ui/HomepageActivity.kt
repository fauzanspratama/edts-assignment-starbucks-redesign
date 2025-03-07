package com.dicoding.starbucksredesign.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.starbucksredesign.adapter.NewsAdapter
import com.dicoding.starbucksredesign.data.NewsItem
import com.dicoding.starbucksredesign.adapter.PromoAdapter
import com.dicoding.starbucksredesign.R
import com.dicoding.starbucksredesign.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding with the correct layout
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply Edge-To-Edge mode after setting the content view
        enableEdgeToEdge()

        // Sample News Data
        val newsList = listOf(
            NewsItem(
                "Food and Drink",
                "Fall in love with Starbucks Valentine’s Day",
                "January 8, 2025",
                "2 min read",
                R.drawable.img_news_1
            ),
            NewsItem(
                "Coffee and Product",
                "Starbucks Reserve Hot Honey Ginger Spritz",
                "January 8, 2025",
                "2 min read",
                R.drawable.img_news_2
            ),
            NewsItem(
                "Coffee and Product",
                "Starbucks Blackberry Sage Refresher Midnight drink",
                "January 8, 2025",
                "2 min read",
                R.drawable.img_news_3
            )
        )

        // Sample Promo Data
        val promoImages = listOf(
            R.drawable.img_promo_1,
            R.drawable.img_promo_2,
            R.drawable.img_promo_3
        )

        // Initialize Promo Adapter
        val promoAdapter = PromoAdapter(promoImages)
        binding.rvPromo.apply {
            layoutManager =
                LinearLayoutManager(this@HomepageActivity)
            adapter = promoAdapter
        }

        // Initialize RecyclerView with Adapter
        newsAdapter = NewsAdapter(newsList)
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = newsAdapter
        }
    }
}
