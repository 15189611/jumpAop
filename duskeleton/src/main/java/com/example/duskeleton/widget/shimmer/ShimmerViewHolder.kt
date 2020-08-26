package com.example.duskeleton.widget.shimmer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.duskeleton.R

/**
 * Author: Charles
 * Version V1.0
 * Date: 2020/8/10
 * Description:
 * Modification History:
 * Date Author Version Description
 */

class ShimmerViewHolder(inflater: LayoutInflater, parent: ViewGroup?, innerViewResId: Int) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.layout_shimmer, parent, false)) {
    init {
        val layout = itemView as ViewGroup
        val view = inflater.inflate(innerViewResId, layout, false)
        val lp = view.layoutParams
        if (lp != null) {
            layout.layoutParams = lp
        }
        layout.addView(view)
    }
}
