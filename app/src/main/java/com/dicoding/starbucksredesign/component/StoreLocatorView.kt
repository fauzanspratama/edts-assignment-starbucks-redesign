package com.dicoding.starbucksredesign.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.dicoding.starbucksredesign.databinding.ViewStoreLocatorBinding

class StoreLocatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewStoreLocatorBinding =
        ViewStoreLocatorBinding.inflate(LayoutInflater.from(context), this, true)

    private var onOrderButtonClick: (() -> Unit)? = null
    private var onLocationBarClick: (() -> Unit)? = null

    init {
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnOrder.setOnClickListener { onOrderButtonClick?.invoke() }
        binding.containerLocation.setOnClickListener { onLocationBarClick?.invoke() }
    }

    fun setStoreData(storeName: String, distance: String) {
        binding.tvStoreName.text = storeName
        binding.tvStoreDistance.text = "$distance km"
    }

    fun setOnOrderButtonClickListener(listener: () -> Unit) {
        this.onOrderButtonClick = listener
    }

    fun setOnLocationBarClickListener(listener: () -> Unit) {
        this.onLocationBarClick = listener
    }
}