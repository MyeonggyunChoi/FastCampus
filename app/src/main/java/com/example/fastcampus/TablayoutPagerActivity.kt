package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class TablayoutPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout_pager)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewpager)

        // 탭 레이아웃에 탭을 추가하는 방법
        tabLayout.addTab(tabLayout.newTab().setText("first"))
        tabLayout.addTab(tabLayout.newTab().setText("second"))
        tabLayout.addTab(tabLayout.newTab().setText("third"))

        // pager에 adapter을 장착하는 방법
        viewPager.adapter = FragmentPagerAdapter(supportFragmentManager,3)

        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.setCurrentItem(tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}

class FragmentPagerAdapter(
    fragmentManager: FragmentManager,
    val tabCount:Int
):FragmentStatePagerAdapter(fragmentManager){
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return FragmentFirst()
            1 -> return FragmentFirst()
            else -> return FragmentFirst()
        }
    }
}