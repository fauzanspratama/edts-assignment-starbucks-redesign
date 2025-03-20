package com.dicoding.starbucksredesign.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.dicoding.starbucksredesign.R
import com.dicoding.starbucksredesign.databinding.ViewNotificationBadgeBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils

class NotificationBadgeView @ExperimentalBadgeUtils
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding: ViewNotificationBadgeBinding
    private var badgeDrawable: BadgeDrawable

    companion object {
        private const val PRESSED_SCALE = 0.95f
        private const val NORMAL_SCALE = 1f
        private const val ANIMATION_DURATION = 100L
    }

    init {
        // Inflate layout using ViewBinding
        binding = ViewNotificationBadgeBinding.inflate(LayoutInflater.from(context), this, true)

        // Create BadgeDrawable
        badgeDrawable = BadgeDrawable.create(context).apply {
            backgroundColor = ContextCompat.getColor(context, R.color.error)
            badgeTextColor = ContextCompat.getColor(context, R.color.white)
            badgeGravity = BadgeDrawable.TOP_END
            maxCharacterCount = 3
            isVisible = false
        }

        // Attach badge to the ImageView
        BadgeUtils.attachBadgeDrawable(badgeDrawable, binding.notificationIcon, this)
    }

    /**
     * Update badge count dynamically
     */
    fun setBadgeCount(count: Int) {
        badgeDrawable.number = count
        badgeDrawable.isVisible = count > 0
    }

    /**
     * Apply button-like press animation to NotificationBadgeView.
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let { motionEvent ->
            if (isEnabled) {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        animateScale(PRESSED_SCALE)
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        animateScale(NORMAL_SCALE)
                        performClick() // Trigger the click event
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * Performs scale animation for press effect.
     */
    private fun animateScale(scale: Float) {
        this.animate()
            .scaleX(scale)
            .scaleY(scale)
            .setDuration(ANIMATION_DURATION)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }
}



