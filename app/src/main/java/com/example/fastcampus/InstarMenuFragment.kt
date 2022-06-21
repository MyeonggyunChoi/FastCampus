package com.example.fastcampus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout

class InstarMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu_instar_bar, container, false)

        view.findViewById<TabLayout>(R.id.tabLayout).apply {
            this.addTab(this.newTab().setIcon(R.drawable.btn_outsta_home).setTag("home"))
            this.addTab(this.newTab().setIcon(R.drawable.btn_outsta_post).setTag("post_upload"))
            this.addTab(this.newTab().setIcon(R.drawable.btn_outsta_my).setTag("profile"))

            this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
                    var moveLock: Boolean = true
                    when (tab?.position) {
                        0 -> {
                            if (moveLock) {
                                val intent = Intent(activity, InstarPageActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                            }
                        }

                        1 -> {
                            if (moveLock) {
                                moveLock = false
                                val data: String? = arguments?.getString("host")
                                when (data) {
                                    "home" -> tabLayout.getTabAt(0)?.select()
                                    "profile" -> tabLayout.getTabAt(2)?.select()
                                }
                                val intent = Intent(activity, InstarPostUploadActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                startActivity(intent)
                                moveLock = true
                            }

                        }

                        2 -> {
                            val intent = Intent(activity, InstarPageActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                            view.findViewById<TabLayout>(R.id.tabLayout).selectTab(tab)
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })
        }

        // 화면 이동하면 자동으로 탭 선택하고록 하고 싶은데..
//        fun selectFirst(){
//            view.findViewById<TabLayout>(R.id.tabLayout).
//        }

        return view
    }


}