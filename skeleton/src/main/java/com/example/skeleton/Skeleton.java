package com.example.skeleton;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.skeleton.widget.RecyclerViewSkeletonScreen;
import com.example.skeleton.widget.ViewSkeletonScreen;

/**
 * Created by Charles
 */

public class Skeleton {

    public static RecyclerViewSkeletonScreen.Builder bind(RecyclerView recyclerView) {
        return new RecyclerViewSkeletonScreen.Builder(recyclerView);
    }

    public static ViewSkeletonScreen.Builder bind(View view) {
        return new ViewSkeletonScreen.Builder(view);
    }

}
