package com.dicoding.starbucksredesign.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.starbucksredesign.R
import com.dicoding.starbucksredesign.adapter.MenuAdapter
import com.dicoding.starbucksredesign.adapter.NewsAdapter
import com.dicoding.starbucksredesign.adapter.PromoAdapter
import com.dicoding.starbucksredesign.component.NotificationBadgeView
import com.dicoding.starbucksredesign.data.MenuItem
import com.dicoding.starbucksredesign.data.NewsItem
import com.dicoding.starbucksredesign.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomepageBinding
    private lateinit var newsAdapter: NewsAdapter
    private var notificationCount = 94
    private var userName = "Diandra"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge() // Apply Edge-To-Edge mode

        // Initialize Dynamic Greetings
        binding.tvUserName.text = "Hi, $userName"

        setupShimmerEffect() // Initialize shimmer effect
        setupHeroBanner()
        setupNotificationBadge()
        setupStoreLocator()
        setupMenuGrid()
        setupNewsSection()
        setupPromoSection()
        setupClickListeners()
        setupBottomNavigation()

    }

    // Setup Hero Banner
    private fun setupHeroBanner() {
        val images = listOf(
            R.drawable.img_homepage_banner_1,
            R.drawable.img_homepage_banner_2,
            R.drawable.img_homepage_banner_3
        )
        binding.viewHeroBanner.setImages(images)
    }

    // Setup Shimmer Effect
    private fun setupShimmerEffect() {
        val shimmerDuration = 14000L  // 14 Sec

        Handler(Looper.getMainLooper()).postDelayed({
            binding.icMemberLevel.stopShimmer()
            binding.icMemberPoint.stopShimmer()
        }, shimmerDuration)
    }


    // Setup Store Locator
    private fun setupStoreLocator() {
        binding.viewStoreLocator.setStoreData("Grand Indonesia", "1.5")
    }

    // Setup Menu Grid
    private fun setupMenuGrid() {
        val menuItems = listOf(
            MenuItem(R.drawable.ic_scan, "Scan & Pay"),
            MenuItem(R.drawable.ic_store_outline, "Store"),
            MenuItem(R.drawable.ic_voucher, "Voucher"),
            MenuItem(R.drawable.ic_order, "My Orders")
        )

        val menuAdapter = MenuAdapter(menuItems)
        binding.rvMenu.apply {
            layoutManager = GridLayoutManager(this@HomepageActivity, 4)
            adapter = menuAdapter
        }
    }

    // Setup News Section
    private fun setupNewsSection() {
        val newsList = listOf(
            NewsItem(
                1,
                "Food and Drink",
                "Fall in love with Starbucks Valentine’s Day",
                "January 8, 2025",
                "2",
                R.drawable.img_news_1
            ), NewsItem(
                2,
                "Coffee and Product",
                "Starbucks Reserve Hot Honey Ginger Spritz",
                "January 8, 2025",
                "2",
                R.drawable.img_news_2
            ), NewsItem(
                3,
                "Coffee and Product",
                "Starbucks Blackberry Sage Refresher Midnight Drink",
                "January 8, 2025",
                "2",
                R.drawable.img_news_3
            )
        )

        newsAdapter = NewsAdapter(this, newsList)
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = newsAdapter
        }
    }

    // Setup Promo Section
    private fun setupPromoSection() {
        val promoImages = listOf(
            R.drawable.img_promo_1, R.drawable.img_promo_2, R.drawable.img_promo_3
        )

        val promoAdapter = PromoAdapter(promoImages)
        binding.rvPromo.apply {
            layoutManager = LinearLayoutManager(this@HomepageActivity)
            adapter = promoAdapter
        }
    }

    // Setup Notification Badge
    private fun setupNotificationBadge() {
        val notificationBadgeView: NotificationBadgeView = binding.viewNotificationBadge

        // Simulating new notifications after a delay
        Handler(Looper.getMainLooper()).postDelayed({
            addNotification(notificationBadgeView)
        }, 2000)

        // Click to update notification count
        notificationBadgeView.setOnClickListener {
            addNotification(notificationBadgeView)
        }
    }


    private fun setupClickListeners() {
        binding.viewStoreLocator.setOnOrderButtonClickListener {
            Toast.makeText(this, "Order Button Clicked!", Toast.LENGTH_SHORT).show()
        }

        binding.viewStoreLocator.setOnLocationBarClickListener {
            Toast.makeText(this, "Location Bar Clicked!", Toast.LENGTH_SHORT).show()
        }
    }


    private fun addNotification(notificationBadgeView: NotificationBadgeView) {
        notificationCount += 1
        notificationBadgeView.setBadgeCount(notificationCount)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "Home Selected", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_card -> {
                    Toast.makeText(this, "Card Selected", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_order -> {
                    Toast.makeText(this, "Order Selected", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_reward -> {
                    Toast.makeText(this, "Reward Selected", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_me -> {
                    Toast.makeText(this, "Me Selected", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> true
            }
        }
    }
}