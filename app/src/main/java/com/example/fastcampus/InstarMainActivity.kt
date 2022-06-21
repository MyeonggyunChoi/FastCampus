package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class InstarMainActivity : AppCompatActivity() {

    val instarPostFragment = InstarPostFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_main)


        val tabs = findViewById<TabLayout>(R.id.main_tab)
        tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_home).setTag("home"))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_post).setTag("post_upload"))
        tabs.addTab(tabs.newTab().setIcon(R.drawable.btn_outsta_my).setTag("profile"))


        val pager = findViewById<ViewPager2>(R.id.main_pager)
        pager.adapter = InstaMainPagerAdapter(this as FragmentActivity, 3, instarPostFragment)

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pager.setCurrentItem(tab!!.position)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

    }
}


class InstaMainPagerAdapter(
    fragmentActivity: FragmentActivity,
    val tabCount: Int,
    val instarPostFragment: InstarPostFragment
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return InstarFeedFragment()
            1 -> return InstarPostFragment()
            else -> return InstarProfileFragment()
        }
    }
}