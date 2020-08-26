package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Author: Charles.pan
 * Version V1.0
 * Date: 2020/7/13
 * Description:
 * Modification History:
 * Date Author Version Description
 * -----------------------------------------------------------------------------------
 * 2020/7/13 Charles.pan 1.0
 * Why & What is modified:
 */
public class Utils {
    public static String getPhoneId(Context context,String name){
        Toast.makeText(context,"提前执行一个toast方法=="  + name,Toast.LENGTH_SHORT).show();
        return "success";
    }





}
