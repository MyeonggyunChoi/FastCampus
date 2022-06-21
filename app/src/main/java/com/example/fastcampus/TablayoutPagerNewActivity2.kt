package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class TablayoutPagerNewActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout_pager_new2)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager2>(R.id.viewpager)

        // 탭 레이아웃에 탭을 추가하는 방법
        tabLayout.addTab(tabLayout.newTab().setText("first"))
        tabLayout.addTab(tabLayout.newTab().setText("second"))
        tabLayout.addTab(tabLayout.newTab().setText("third"))

        // pager에 adapter을 장착하는 방법
        viewPager.adapter = FragmentAdapter2(this,3)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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

class FragmentAdapter2(
    fragmentActivity: FragmentActivity,
    val tabCount: Int
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FragmentFirst()
            1 -> return FragmentFirst()
            else -> return FragmentFirst()
        }
    }
    //    override fun getCount(): Int {
//        return tabCount
//    }
//
//    override fun getItem(position: Int): Fragment {
//        when (position) {
//            0 -> return FragmentFirst()
//            1 -> return FragmentFirst()
//            else -> return FragmentFirst()
//        }
//    }
}