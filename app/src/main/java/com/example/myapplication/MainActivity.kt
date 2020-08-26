package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  test =  MyTest("Charles hello !");
        test.printlnLog()
    }


    class MyTest constructor(var sss : String) {
        init {
            Log.e("Charles", "init==$sss")
        }

        fun printlnLog(){
            Log.e("Charles",sss)

        }
    }

}
