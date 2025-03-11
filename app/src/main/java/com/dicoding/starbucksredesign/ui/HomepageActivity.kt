package com.dicoding.starbucksredesign.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.starbucksredesign.R
import com.dicoding.starbucksredesign.adapter.NewsAdapter
import com.dicoding.starbucksredesign.adapter.PromoAdapter
import com.dicoding.starbucksredesign.data.NewsItem
import com.dicoding.starbucksredesign.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply Edge-To-Edge mode
        enableEdgeToEdge()


        // Sample News Data
        val newsList = listOf(
            NewsItem(
                1,
                "Food and Drink",
                "Fall in love with Starbucks Valentine’s Day",
                "January 8, 2025",
                "2",
                R.drawable.img_news_1
            ),
            NewsItem(
                2,
                "Coffee and Product",
                "Starbucks Reserve Hot Honey Ginger Spritz",
                "January 8, 2025",
                "2",
                R.drawable.img_news_2
            ),
            NewsItem(
                3,
                "Coffee and Product",
                "Starbucks Blackberry Sage Refresher Midnight Drink",
                "January 8, 2025",
                "2",
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
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = promoAdapter
        }

        // Initialize News Adapter
        newsAdapter = NewsAdapter(this, newsList) // ✅ Fixed order (Activity first)
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = newsAdapter
        }
    }
}
