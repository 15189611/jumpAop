package com.example.duskeleton.widget.recycler

import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.IntRange
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.duskeleton.R
import com.example.duskeleton.`interface`.SkeletonScreen

/**
 * Author: Charles
 * Version V1.0
 * Date: 2020/8/10
 * Description:
 * Modification History:
 * Date Author Version Description
 */
class RecyclerViewSkeletonScreen(builder: Builder) : SkeletonScreen {

    private var mRecyclerView: RecyclerView? = null
    private var mActualAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var mSkeletonAdapter: SkeletonAdapter? = null
    private var mRecyclerViewFrozen = false

    init {
        mRecyclerView = builder.mRecyclerView
        mActualAdapter = builder.mActualAdapter
        mSkeletonAdapter = SkeletonAdapter()
        mSkeletonAdapter?.itemCount = builder.mItemCount
        mSkeletonAdapter?.setLayoutReference(builder.mItemResID)
        mSkeletonAdapter?.setArrayOfLayoutReferences(builder.mItemsResIDArray)
        mSkeletonAdapter?.shimmer(builder.mShimmer)
        mSkeletonAdapter?.setShimmerColor(builder.mShimmerColor)
        mSkeletonAdapter?.setShimmerAngle(builder.mShimmerAngle)
        mSkeletonAdapter?.setShimmerDuration(builder.mShimmerDuration)
        mRecyclerViewFrozen = builder.mFrozen
    }

    override fun show() {
        mRecyclerView?.adapter = mSkeletonAdapter
        val isComputingLayout = mRecyclerView?.isComputingLayout ?: false
        if (!isComputingLayout && mRecyclerViewFrozen) {
            mRecyclerView?.isLayoutFrozen = true
        }
    }

    override fun hide() {
        mRecyclerView?.adapter = mActualAdapter
    }

    class Builder(recyclerView: RecyclerView) {
        var mActualAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
        val mRecyclerView: RecyclerView = recyclerView
        var mShimmer = true
        var mItemCount = 10
        var mItemResID: Int = R.layout.layout_default_item_skeleton
        var mItemsResIDArray: IntArray = intArrayOf()
        var mShimmerColor: Int
        var mShimmerDuration = 1000
        var mShimmerAngle = 20
        var mFrozen = true

        init {
            mShimmerColor = ContextCompat.getColor(
                recyclerView.context,
                R.color.shimmer_color
            )
        }

        /**
         * @param adapter the target recyclerView actual adapter
         */
        fun adapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>): Builder {
            mActualAdapter = adapter
            return this
        }

        /**
         * @param itemCount the child item count in recyclerView
         */
        fun count(itemCount: Int): Builder {
            mItemCount = itemCount
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
         * @param shimmerColor the shimmer color
         */
        fun color(@ColorRes shimmerColor: Int): Builder {
            mShimmerColor = ContextCompat.getColor(
                mRecyclerView.context,
                shimmerColor
            )
            return this
        }

        /**
         * @param shimmerAngle the angle of the shimmer effect in clockwise direction in degrees.
         */
        fun angle(@IntRange(from = 0, to = 30) shimmerAngle: Int): Builder {
            mShimmerAngle = shimmerAngle
            return this
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        fun load(@LayoutRes skeletonLayoutResID: Int): Builder {
            mItemResID = skeletonLayoutResID
            return this
        }

        /**
         * @param skeletonLayoutResIDs the loading array of skeleton layoutResID
         */
        fun loadArrayOfLayouts(@ArrayRes skeletonLayoutResIDs: IntArray): Builder {
            mItemsResIDArray = skeletonLayoutResIDs
            return this
        }

        /**
         * @param frozen whether frozen recyclerView during skeleton showing
         * @return
         */
        fun frozen(frozen: Boolean): Builder {
            mFrozen = frozen
            return this
        }

        fun show(): RecyclerViewSkeletonScreen {
            val recyclerViewSkeleton = RecyclerViewSkeletonScreen(this)
            recyclerViewSkeleton.show()
            return recyclerViewSkeleton
        }

    }
}
