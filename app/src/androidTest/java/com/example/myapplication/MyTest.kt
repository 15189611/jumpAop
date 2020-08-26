package com.example.myapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

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
@RunWith(AndroidJUnit4::class)
class MyTest {
    @Test
    fun useAppContext() {
        val test =  MyTest();

    }


    class MyTest constructor(var sss : String) {
//        init {
//            println(sss)
//        }

        fun printlnLog(){
            sss.hashCode()
        }
    }
}
