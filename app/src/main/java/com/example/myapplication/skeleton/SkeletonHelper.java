package com.example.myapplication.skeleton;

import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duskeleton.Skeleton;
import com.example.duskeleton.widget.ViewSkeletonScreen;
import com.example.duskeleton.widget.recycler.RecyclerViewSkeletonScreen;
import com.example.myapplication.R;


/**
 * Author: Charles
 * Version V1.0
 * Date: 2020/8/10
 * Description:
 * Modification History:
 * Date Author Version Description
 */
public class SkeletonHelper {

    private ViewSkeletonScreen mSkeletonScreen;
    private RecyclerViewSkeletonScreen recyclerViewSkeletonScreen;

    private SkeletonHelper() {

    }

    private static class Holder {
        private static SkeletonHelper instance = new SkeletonHelper();
    }

    public static SkeletonHelper getInstance() {
        return Holder.instance;
    }

    public void showViewSkeletonImage(View rootView, @DrawableRes int imageResource) {
        if (rootView == null || imageResource == 0) {
            return;
        }

        //初始化

        mSkeletonScreen = Skeleton.INSTANCE.bind(rootView)
                .loadImage(imageResource)
                .shimmer(false)
                .show();
    }

    public void showViewSkeleton(View rootView, int layoutId) {
        if (rootView == null || layoutId == 0) {
            return;
        }

        //初始化

        mSkeletonScreen = Skeleton.INSTANCE.bind(rootView)
                .load(layoutId)
                .shimmer(false)
                .show();
    }

    public void showRecyclerViewSkeleton(RecyclerView recyclerView, RecyclerView.Adapter adapter, int layoutId) {
        if (recyclerView == null || adapter == null) {
            return;
        }

        recyclerViewSkeletonScreen = Skeleton.INSTANCE.bind(recyclerView)
                .adapter(adapter)
                .load(layoutId == 0 ? R.layout.layout_default_item_skeleton : layoutId)
                .shimmer(false)
                .color(R.color.light_transparent)
                .duration(2000)
                .show();
    }

    public void hideSkeleton() {
        if (mSkeletonScreen != null) {
            mSkeletonScreen.hide();
        }

        if (recyclerViewSkeletonScreen != null) {
            recyclerViewSkeletonScreen.hide();
        }

        mSkeletonScreen = null;
        recyclerViewSkeletonScreen = null;
    }
}
