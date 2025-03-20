package com.dicoding.starbucksredesign.component

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.dicoding.starbucksredesign.adapter.HeroBannerAdapter
import com.dicoding.starbucksredesign.databinding.ViewHeroBannerBinding
import com.google.android.material.tabs.TabLayoutMediator

class HeroBannerView @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewHeroBannerBinding =
        ViewHeroBannerBinding.inflate(LayoutInflater.from(context), this, true)

    private var imageList: List<Int> = emptyList()
    private val sliderHandler = Handler(Looper.getMainLooper())
    private val slideRunnable = object : Runnable {
        override fun run() {
            val nextItem = (binding.vpHeaderBanner.currentItem + 1) % imageList.size
            binding.vpHeaderBanner.currentItem = nextItem
            sliderHandler.postDelayed(this, 3000) // Change slide every 3 seconds
        }
    }

    init {
        setupAutoScroll()
    }

    fun setImages(images: List<Int>) {
        imageList = images
        binding.vpHeaderBanner.adapter = HeroBannerAdapter(imageList)

        // Bind TabLayout with ViewPager2
        TabLayoutMediator(binding.tabIndicator, binding.vpHeaderBanner) { _, _ -> }.attach()

        restartAutoScroll()
    }

    private fun setupAutoScroll() {
        binding.vpHeaderBanner.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                sliderHandler.removeCallbacks(slideRunnable)
                sliderHandler.postDelayed(slideRunnable, 3000)
            }
        })
    }

    private fun restartAutoScroll() {
        sliderHandler.removeCallbacks(slideRunnable)
        sliderHandler.postDelayed(slideRunnable, 3000)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        sliderHandler.removeCallbacks(slideRunnable) // Prevent memory leaks
    }
}