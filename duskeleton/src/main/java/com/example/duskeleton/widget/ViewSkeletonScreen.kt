package com.example.duskeleton.widget

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.example.duskeleton.R
import com.example.duskeleton.widget.shimmer.ShimmerLayout

/**
 * Author: Charles
 * Version V1.0
 * Date: 2020/8/10
 * Description:
 * Modification History:
 * Date Author Version Description
 */
class ViewSkeletonScreen(builder: Builder) {
    private val TAG: String = ViewSkeletonScreen::class.java.name
    private var mViewReplacer: ViewReplacer? = null
    private var mActualView: View? = null
    private var mSkeletonResID = 0
    private var mShimmerColor = 0
    private var mShimmer = false
    private var mShimmerDuration = 0
    private var mShimmerAngle = 0
    private var mImageResource = 0

    init {
        mActualView = builder.mView
        mSkeletonResID = builder.mSkeletonLayoutResID
        mShimmer = builder.mShimmer
        mShimmerDuration = builder.mShimmerDuration
        mShimmerAngle = builder.mShimmerAngle
        mShimmerColor = builder.mShimmerColor
        mImageResource = builder.mImageResource
        mViewReplacer = ViewReplacer(builder.mView)
    }

    private fun generateShimmerContainerLayout(parentView: ViewGroup): ShimmerLayout? {
        val shimmerLayout: ShimmerLayout =
            LayoutInflater.from(mActualView?.context)
                .inflate(R.layout.layout_shimmer, parentView, false) as ShimmerLayout
        shimmerLayout.setShimmerColor(mShimmerColor)
        shimmerLayout.setShimmerAngle(mShimmerAngle)
        shimmerLayout.setShimmerAnimationDuration(mShimmerDuration)
        val innerView =
            LayoutInflater.from(mActualView?.context)
                .inflate(mSkeletonResID, shimmerLayout, false)
        val lp = innerView.layoutParams
        if (lp != null) {
            shimmerLayout.layoutParams = lp
        }
        shimmerLayout.addView(innerView)
        shimmerLayout.addOnAttachStateChangeListener(object :
            View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                shimmerLayout.startShimmerAnimation()
            }

            override fun onViewDetachedFromWindow(v: View) {
                shimmerLayout.stopShimmerAnimation()
            }
        })
        shimmerLayout.startShimmerAnimation()
        return shimmerLayout
    }

    private fun generateSkeletonLoadingView(): View? {
        Log.e(TAG, "mActualView = $mActualView")

        val viewParent = mActualView?.parent
        if (viewParent == null) {
            Log.e(TAG, "the source view have not attach to any view")
            return null
        }

        Log.e(TAG, "viewParent = $viewParent")

        val parentView = viewParent as ViewGroup
        return when {
            mShimmer -> {
                generateShimmerContainerLayout(parentView)
            }
            mImageResource != 0 -> {
                return EmptyLayout(mActualView?.context).setImage(mImageResource)
            }
            else -> LayoutInflater.from(mActualView?.context)
                .inflate(mSkeletonResID, parentView, false)
        }
    }

    fun show() {
        val skeletonLoadingView = generateSkeletonLoadingView()
        Log.e(TAG, "need to replace layout view = $skeletonLoadingView")
        if (skeletonLoadingView != null) {
            mViewReplacer?.replace(skeletonLoadingView)
        }
    }

    fun hide() {
        if (mViewReplacer?.getTargetView() is ShimmerLayout) {
            (mViewReplacer?.getTargetView() as ShimmerLayout).stopShimmerAnimation()
        }
        mViewReplacer?.restore()
    }

    class Builder(val mView: View) {
        var mSkeletonLayoutResID = 0
        var mImageResource = 0
        var mShimmer = true
        var mShimmerColor: Int
        var mShimmerDuration = 1000
        var mShimmerAngle = 20

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        fun load(@LayoutRes skeletonLayoutResID: Int): Builder {
            mSkeletonLayoutResID = skeletonLayoutResID
            return this
        }

        /**
         * @param imageResource the loading skeleton DrawableRes
         */
        fun loadImage(@DrawableRes imageResource: Int): Builder {
            mImageResource = imageResource
            return this
        }

        /**
         * @param shimmerColor the shimmer color
         */
        fun color(@ColorRes shimmerColor: Int): Builder {
            mShimmerColor = ContextCompat.getColor(mView.context, shimmerColor)
            return this
        }

        /**
         * @param shimmer whether show shimmer animation
         */
        fun shimmer(shimmer: Boolean): Builder {
            mShimmer = shimmer
            return this
        }

        /**
         * the duration of the animation , the time it will take for the highlight to move from one end of the layout
         * to the other.
         *
         * @param shimmerDuration Duration of the shimmer animation, in milliseconds
         */
        fun duration(shimmerDuration: Int): Builder {
            mShimmerDuration = shimmerDuration
            return this
        }

        /**
         * @param shimmerAngle the angle of the shimmer effect in clockwise direction in degrees.
         */
        fun angle(@IntRange(from = 0, to = 30) shimmerAngle: Int): Builder {
            mShimmerAngle = shimmerAngle
            return this
        }

        fun show(): ViewSkeletonScreen {
            val skeletonScreen = ViewSkeletonScreen(this)
            skeletonScreen.show()
            return skeletonScreen
        }

        init {
            mShimmerColor = ContextCompat.getColor(
                mView.context,
                R.color.shimmer_color
            )
        }
    }
}
