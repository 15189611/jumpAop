package com.example.myapplication.skeleton;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.MyAdapter;
import com.example.myapplication.R;
import com.willowtreeapps.spruce.Spruce;
import com.willowtreeapps.spruce.animation.DefaultAnimations;
import com.willowtreeapps.spruce.sort.DefaultSort;

import java.util.ArrayList;
import java.util.List;


public class SkeletonActivity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton);

        initRecyclerView();

    }

    private void initRecyclerView() {
        View parentViewGroup = findViewById(R.id.parentLayout);
        rv = findViewById(R.id.recyclerView);
        List<String> lists = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            lists.add("Charles==" + i);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(linearLayoutManager);
        final MyAdapter myAdapter = new MyAdapter(this, lists);
        SkeletonHelper.getInstance().showViewSkeleton(parentViewGroup,R.layout.activity_view_skeleton);
        SkeletonHelper.getInstance().showRecyclerViewSkeleton(rv, myAdapter, 0);


        Animator spruceAnimator = new Spruce
                .SpruceBuilder((ViewGroup) parentViewGroup)
                .sortWith(new DefaultSort(/*interObjectDelay=*/50L))
                .animateWith(new Animator[] {DefaultAnimations.shrinkAnimator(parentViewGroup, /*duration=*/800)})
                .start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SkeletonHelper.getInstance().hideSkeleton();
                rv.setAdapter(myAdapter);
            }
        }, 500);

    }
}
