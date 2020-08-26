package com.example.duskeleton

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.duskeleton.widget.ViewSkeletonScreen
import com.example.duskeleton.widget.recycler.RecyclerViewSkeletonScreen

/**
 * Author: Charles
 * Version V1.0
 * Date: 2020/8/10
 * Description:
 * Modification History:
 * Date Author Version Description
 */
object Skeleton {
    fun bind(recyclerView: RecyclerView): RecyclerViewSkeletonScreen.Builder {
        return RecyclerViewSkeletonScreen.Builder(recyclerView)
    }

    fun bind(view: View): ViewSkeletonScreen.Builder {
        return ViewSkeletonScreen.Builder(view)
    }

}
