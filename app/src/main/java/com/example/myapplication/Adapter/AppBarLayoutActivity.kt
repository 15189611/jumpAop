package com.example.myapplication.Adapter

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.skeleton.SkeletonHelper
import com.example.myapplication.widget.BrandTabLayout


/**
 * Author: Charles.pan
 * Version V1.0
 * Date: 2020/7/15
 * Description:
 * Modification History:
 * Date Author Version Description
 * -----------------------------------------------------------------------------------
 * 2020/7/15 Charles.pan 1.0
 * Why & What is modified:
 */
class AppBarLayoutActivity : AppCompatActivity() {
    val tabs = mutableListOf<String>("商品", "动态")

    open fun dip2px(dipValue: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue + 0.5F, this@AppBarLayoutActivity.resources.displayMetrics
        ).toInt()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appbar)
        val mTabLayout = findViewById<BrandTabLayout>(R.id.tabLayout)
        // 添加多个tab
        // 添加多个tab
        for (element in tabs) {
            val tab: BrandTabLayout.Tab = mTabLayout.newTab()
            val textView = TextView(applicationContext).also {
                it.text = element
                it.setTextColor(mTabLayout.tabTextColors)

                if (element == "商品") {
                    it.gravity = Gravity.CENTER
                    it.setPadding(0, 0, dip2px(30), 0)
                } else {
                    it.gravity = Gravity.CENTER
                    it.setPadding(dip2px(30), 0, 0, 0)
                }
                it.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16f)
            }
            tab.customView = textView
            mTabLayout.addTab(tab)
        }
        mTabLayout.setSelectedTabIndicator(resources.getDrawable(R.drawable.brand_tab_indicator_shape))

        val mViewPager = findViewById<ViewPager>(R.id.viewPager)
        val mAdapter = ViewPagerAdapter(supportFragmentManager)
        val myPageFragment = MyPageFragment();
        myPageFragment.setICallBack {
            Log.e("Charles","完成")
            SkeletonHelper.getInstance().hideSkeleton()
//            Handler().postDelayed(Runnable {
//
//            }, 500)
        }
        mAdapter.addFragment(myPageFragment, tabs[0])
        mAdapter.addFragment(MyPageFragment2(), tabs[1])
//       mAdapter.addFragment(MyPageFragment3(),tabs[2])
        mViewPager.adapter = mAdapter


        mTabLayout.addOnTabSelectedListener(object : BrandTabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: BrandTabLayout.Tab?) {
                Log.e("Charles", "onTabReselected==" + tab!!.position)
            }

            override fun onTabUnselected(tab: BrandTabLayout.Tab?) {
                Log.e("Charles", "onTabUnselected==" + tab!!.position)
            }

            override fun onTabSelected(tab: BrandTabLayout.Tab?) {
                Log.e("Charles", "onTabSelected==" + tab!!.position)
                if (tab.position == 0) {
                    mTabLayout.setSelectedTabIndicator(resources.getDrawable(R.drawable.brand_tab_indicator_shape))

                } else {
                    mTabLayout.setSelectedTabIndicator(resources.getDrawable(R.drawable.brand_tab_indicator_shape_right))

                }
            }
        })

        mViewPager.addOnPageChangeListener(BrandTabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mTabLayout.addOnTabSelectedListener(BrandTabLayout.ViewPagerOnTabSelectedListener(mViewPager))


        // mTabLayout.setupWithViewPager(mViewPager);

    }

    override fun onResume() {
        super.onResume()
//        SkeletonHelper.getInstance()
//            .showViewSkeleton(findViewById(R.id.parentView), R.layout.test_skeleton)
    }

    internal class ViewPagerAdapter(manager: FragmentManager?) :
        FragmentStatePagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

}
