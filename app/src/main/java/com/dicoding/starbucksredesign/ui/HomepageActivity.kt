package com.dicoding.starbucksredesign.ui

import android.os.Bundle
import android.widget.Toast
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


        // Update Store Data Dynamically
        binding.viewStoreLocator.setStoreData("Grand Indonesia", "1.5")


        // Initialize News Adapter
        newsAdapter = NewsAdapter(this, newsList)
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = newsAdapter
        }

        // Initialize Promo Adapter
        val promoAdapter = PromoAdapter(promoImages)
        binding.rvPromo.apply {
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = promoAdapter
        }

        // Set Click Listeners
        binding.viewStoreLocator.setOnOrderButtonClickListener {
            Toast.makeText(this, "Order Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.viewStoreLocator.setOnLocationBarClickListener {
            Toast.makeText(this, "Location Bar Clicked!", Toast.LENGTH_SHORT).show()
        }

        val images = listOf(
            R.drawable.img_homepage_banner,
            R.drawable.img_homepage_banner,
            R.drawable.img_homepage_banner
        )

        binding.viewHeroBanner.setImages(images)
    }
}
