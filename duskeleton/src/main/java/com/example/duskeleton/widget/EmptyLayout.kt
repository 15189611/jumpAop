package com.example.duskeleton.widget

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.example.duskeleton.R

/**
 * Author: Charles
 * Version V1.0
 * Date: 2020/8/19
 * Description:
 * Modification History:
 * Date Author Version Description
 */
class EmptyLayout(context: Context?) {
    private var context: Context? = null
    private lateinit var statusView: View

    init {
        this.context = context
        inflateView()
    }

    private fun inflateView() {
        statusView = LayoutInflater.from(context).inflate(R.layout.layout_image_empty, null)
    }


    fun setImage(@DrawableRes imageResource: Int): View {
        val imageView = statusView.findViewById<ImageView>(R.id.skeleton_img)
        imageView?.setImageResource(imageResource)
        //imageView?.adjustViewBounds = true
        //
        val width = imageView.drawable.bounds.width()
        val height = imageView.drawable.bounds.height()
        Log.e("Charles", "density width = " + width + ",height=" + height)

        return statusView
    }

}
