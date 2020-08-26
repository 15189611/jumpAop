package com.example.duskeleton.widget

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import java.util.*

/**
 * Author: Charles
 * Version V1.0
 * Date: 2020/8/10
 * Description:
 * Modification History:
 * Date Author Version Description
 */
class ViewReplacer(sourceView: View) {

    private val TAG: String = ViewReplacer::class.java.name
    private var mSourceView: View = sourceView
    private var mTargetView: View? = null
    private var mTargetViewResID = -1
    private var mCurrentView: View? = null
    private var mSourceParentView: ViewGroup? = null
    private var mSourceViewLayoutParams: ViewGroup.LayoutParams? = null
    private var mSourceViewIndexInParent = 0
    private var mSourceViewId = 0
    private var mCurrentParentId = 65535

    init {
        val pars = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val layoutParams = sourceView.layoutParams
        val height = layoutParams.height
        val width = layoutParams.width
        Log.e("Charles", "height==" + height + "width==" + width)
        mSourceViewLayoutParams = pars
        mCurrentView = mSourceView
        mSourceViewId = mSourceView.id
    }


    fun replace(targetViewResID: Int) {
        if (mTargetViewResID == targetViewResID) {
            return
        }
        if (init()) {
            mTargetViewResID = targetViewResID
            replace(
                LayoutInflater.from(mSourceView.context)
                    .inflate(mTargetViewResID, mSourceParentView, false)
            )
        }
    }

    fun replace(targetView: View) {
        if (mCurrentView === targetView) {
            return
        }

        Log.e(TAG, "currentView = $mCurrentView")
        if (targetView.parent != null) {
            (targetView.parent as ViewGroup).removeView(targetView)
        }
        mTargetView = targetView

        if (init()) {
            mCurrentView?.visibility = View.INVISIBLE
            mTargetView?.id = mSourceViewId
            mSourceParentView?.addView(
                mTargetView,
                mSourceViewIndexInParent,
                mSourceViewLayoutParams
            )
            mCurrentView = mTargetView
        }
    }

    fun restore() {
        if (mTargetView == null) {
            return
        }

        if (mSourceParentView != null) {
            if (mSourceParentView is ViewPager) {
                restoreHandleViewPager()
                return
            } else if (mSourceView.parent is LinearLayout) {
                restoreLayout()
                return
            }

            mSourceParentView?.removeView(mCurrentView)
            mSourceView.visibility = View.VISIBLE
            mCurrentView = mSourceView
            mTargetView = null
            mTargetViewResID = -1
        }
    }


    fun getSourceView(): View? {
        return mSourceView
    }

    fun getTargetView(): View? {
        return mTargetView
    }

    fun getCurrentView(): View? {
        return mCurrentView
    }


    private fun init(): Boolean {
        if (mSourceParentView == null) {
            mSourceParentView = mSourceView.parent as ViewGroup
            if (mSourceParentView == null) {
                Log.e(TAG, "the source view have not attach to any view")
                return false
            }

            Log.e("Charles", "parent=$mSourceParentView")
            when (mSourceParentView) {
                is LinearLayout -> {
                    handleAddLayout()
                    getSourceViewIndexInParent()
                }
                is ViewPager -> {
                    handleViewPager()
                    return false
                }
                else -> {
                    getSourceViewIndexInParent()
                }
            }
        }
        return true
    }

    private fun handleViewPager() {
        val currentSourceParent = mSourceView as ViewGroup

        val currentCount = currentSourceParent.childCount
        for (index in 0 until currentCount) {
            val childView = currentSourceParent.getChildAt(index)
            Log.e("Charles", "childView==$childView")
            childView.visibility = View.INVISIBLE
        }

        currentSourceParent.addView(
            mTargetView,
            mSourceViewIndexInParent,
            mSourceViewLayoutParams
        )
        mCurrentView = mTargetView

    }

    private fun restoreHandleViewPager() {
        val currentSourceParent = mSourceView as ViewGroup
        currentSourceParent.removeView(mCurrentView)

        val currentCount = currentSourceParent.childCount
        for (index in 0 until currentCount) {
            val childView = currentSourceParent.getChildAt(index)
            Log.e("Charles", "childView==$childView")
            childView.visibility = View.VISIBLE
        }
        mCurrentView = mSourceView
        mTargetView = null
        mTargetViewResID = -1

    }

    private fun restoreLayout() {
        mSourceParentView?.removeView(mCurrentView)
        mSourceView.visibility = View.VISIBLE
        mCurrentView = mSourceView
        mTargetView = null
        mTargetViewResID = -1
    }

    private fun handleAddLayout() {
        val currentCount = mSourceParentView?.childCount ?: 0
        Log.e("Charles", "currentCount=$currentCount")
        val currentParent = LinearLayout(mSourceView.context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        currentParent.layoutParams = params
        currentParent.id = mCurrentParentId
        smartLayoutParent = currentParent
        var currentIndex = 0

        for (index in 0 until currentCount) {
            val childView = mSourceParentView?.getChildAt(index)
            if (mSourceView === childView) {
                currentIndex = index
                val parent = childView.parent as ViewGroup
                parent.removeViewAt(currentIndex)
                currentParent.addView(childView)
                break
            }
        }
        mSourceParentView?.addView(currentParent, currentIndex)
        mSourceParentView = currentParent
    }

    var smartLayoutParent: ViewGroup? = null

    private fun getSourceViewIndexInParent() {
        val count = mSourceParentView?.childCount ?: 0
        for (index in 0 until count) {
            if (mSourceView === mSourceParentView?.getChildAt(index)) {
                mSourceViewIndexInParent = index
                break
            }
        }
    }
}
