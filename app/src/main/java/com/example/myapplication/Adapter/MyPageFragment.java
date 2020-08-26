package com.example.myapplication.Adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.skeleton.SkeletonHelper;

import java.util.ArrayList;
import java.util.List;


public class MyPageFragment extends Fragment {
    private View parentViewGroup;
    private View rootView;
    private MyAdapter myAdapter;
    private RecyclerView rv;
    private ICallBack iCallBack;

    public void setICallBack(ICallBack iCallBack) {
        this.iCallBack = iCallBack;
    }

    public interface ICallBack {
        void finishData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;
        int height3 = dm.heightPixels;
        Log.e("Charles", "density==" + density1 + "width==" + width3 + ",height=" + height3);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.skeleton_fragment_low);
        Log.e("Charles", "width: " + bitmap.getWidth());
        Log.e("Charles", "height: " + bitmap.getHeight());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view1, container, false);
        initView(rootView);
        //SkeletonHelper.getInstance().showRecyclerViewSkeleton(rv,myAdapter,0);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SkeletonHelper.getInstance().showViewSkeletonImage(rootView.findViewById(R.id.rv), R.mipmap.skeleton_fragment_low);
    }

    private void initView(View rootView) {
        rv = rootView.findViewById(R.id.rv);

        List<String> lists = new ArrayList<String>();
        for (int i = 0; i <= 15; i++) {
            lists.add("Charles==" + i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(getActivity(), lists);
        rv.setAdapter(myAdapter);
        iCallBack.finishData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //SkeletonHelper.getInstance().hideSkeleton();
            }
        }, 5000);

    }


}

