package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Adapter.AppBarLayoutActivity;


/**
 * Author: Charles.pan
 * Version V1.0
 * Date: 2020/7/12
 * Description:
 * Modification History:
 * Date Author Version Description
 * -----------------------------------------------------------------------------------
 * 2020/7/12 Charles.pan 1.0
 * Why & What is modified:
 */
public class TestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //test("你好", "明天干嘛@");
        //@ActivityAutoStart(value = "Adapter.AppBarLayoutActivity")
        setContentView(R.layout.activity_main);
        spanTest();

        //SkeletonHelper.getInstance().showViewSkeletonImage(findViewById(R.id.constrainLayout),R.mipmap.skeleton_mall_tab_list_fragment);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // SkeletonHelper.getInstance().hideSkeleton();
            }
        }, 3000);
    }


//    @DebugLog(value = "debug的注解")
//    private String test(String first, String second) {
//        return "Charles" + first + ",," + second;
//    }

    private void spanTest(){
        String content = StringUtils.INSTANCE.formatText("你是我发 是", 13);
        Log.e("Charles","content---"+ content);
        Intent intent =  new Intent(this,TestActivity.class);
        startActivity(new Intent(this, AppBarLayoutActivity.class));
    }

}
