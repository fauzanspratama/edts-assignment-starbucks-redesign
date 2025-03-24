package com.dicoding.starbucksredesign.component

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import com.dicoding.starbucksredesign.R

class ShimmerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    // Paint used to draw the shimmer effect over the children.
    private val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        isDither = true
        isFilterBitmap = true
        // Set up the blend mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            blendMode = BlendMode.SRC_ATOP
        } else {
            @Suppress("DEPRECATION")
            xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
        }
    }

    // Shimmer configuration
    private val shimmerDuration = 1200L
    private val shimmerAngle = 25 // in degrees
    private val shimmerWidth = resources.getDimensionPixelSize(R.dimen.shimmer_width_default)
    private val shimmerCenterWidth = resources.getDimensionPixelSize(R.dimen.shimmer_width_center_default)
    private val shimmerColor = context.getColor(R.color.white)

    private var shimmerAnimator: ValueAnimator? = null
    private var translateX = 0f
    private var shader: LinearGradient? = null

    init {
        setWillNotDraw(false)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0) {
            // Create the gradient shader once using the view's width.
            shader = createShimmerShader(w, shimmerAngle.toDouble(), shimmerWidth, shimmerCenterWidth)
            maskPaint.shader = shader
            startShimmer()
        }
    }

    /**
     * Creates a horizontal LinearGradient shader along the given angle.
     */
    private fun createShimmerShader(width: Int, angle: Double, shadowWidth: Int, shadowCenter: Int): LinearGradient {
        val angleInRadians = Math.toRadians(angle)
        val startX = 0f
        val startY = 0f
        val endX = (Math.cos(angleInRadians) * width).toFloat()
        val endY = (Math.sin(angleInRadians) * width).toFloat()

        // Stops for the gradient (transparent at the edges and shimmerColor in the center).
        val stops = floatArrayOf(0.0f, 0.5f, 1.0f)
        return LinearGradient(
            startX, startY, endX, endY,
            intArrayOf(Color.TRANSPARENT, shimmerColor, Color.TRANSPARENT),
            stops,
            Shader.TileMode.CLAMP
        )
    }

    /**
     * Starts a ValueAnimator that updates the horizontal translation of the shader.
     */
    private fun startShimmer() {
        shimmerAnimator?.cancel()
        shimmerAnimator = ValueAnimator.ofFloat(-width.toFloat(), width.toFloat()).apply {
            duration = shimmerDuration
            repeatCount = ValueAnimator.INFINITE
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animator ->
                translateX = animator.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        // First, draw the child views normally.
        super.dispatchDraw(canvas)
        // Now, save the current canvas state.
        canvas.save()
        // Update the shader's local matrix to translate horizontally.
        shader?.setLocalMatrix(Matrix().apply { setTranslate(translateX, 0f) })
        // Draw the shimmer effect over the children.
        canvas.drawPaint(maskPaint)
        // Restore the canvas to its previous state.
        canvas.restore()
    }

    override fun onDetachedFromWindow() {
        shimmerAnimator?.cancel()
        super.onDetachedFromWindow()
    }

    /**
     * Public method to stop the shimmer animation.
     */
    fun stopShimmer() {
        shimmerAnimator?.cancel() // Stop the shimmer animation

        val fadeOutAnimator = ValueAnimator.ofFloat(1f, 0f).apply {
            duration = 500 // Smooth fade-out duration
            interpolator = LinearInterpolator()
            addUpdateListener { animator ->
                val alphaValue = animator.animatedValue as Float
                maskPaint.alpha = (alphaValue * 255).toInt() // Reduce shimmer visibility gradually
                invalidate() // Redraw without removing the view
            }
            start()
        }

        fadeOutAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator) {
                maskPaint.shader = null // Remove shimmer shader after fade-out
                invalidate() // Redraw view without shimmer
            }

            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
    }
}