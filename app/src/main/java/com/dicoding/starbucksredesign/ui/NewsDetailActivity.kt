package com.dicoding.starbucksredesign.ui

import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.starbucksredesign.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Enable transitions before calling super.onCreate()
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        /**
        // Deprecated
        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
         */


        // Get data from intent
        val id = intent.getIntExtra("news_id", -1)
        val title = intent.getStringExtra("news_title")
        val category = intent.getStringExtra("news_category")
        val date = intent.getStringExtra("news_date")
        val readTime = intent.getStringExtra("news_read_time")
        val imageResId = intent.getIntExtra("news_image", 0)

        // Set transition names dynamically
        binding.ivNewsImage.transitionName = "news_image_$id"


        /**
        // Apply smoother MaterialContainerTransform
        window.sharedElementEnterTransition = MaterialContainerTransform().apply {
        addTarget(binding.root)
        duration = 450L  // Smoother, faster transition
        interpolator = AccelerateDecelerateInterpolator()
        scrimColor = Color.TRANSPARENT  // No background fade
        pathMotion = MaterialArcMotion()
        }

        window.sharedElementReturnTransition = MaterialContainerTransform().apply {
        addTarget(binding.root)
        duration = 450L
        interpolator = AccelerateDecelerateInterpolator()
        scrimColor = Color.TRANSPARENT
        pathMotion = MaterialArcMotion()
        }
         */


        // Set data to views
        binding.tvNewsTitle.text = title
        binding.tvNewsCategory.text = category
        binding.tvNewsDate.text = date
        binding.tvNewsReadTime.text = "$readTime read time"
        binding.ivNewsImage.setImageResource(imageResId)

        // Handle back button click
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}
