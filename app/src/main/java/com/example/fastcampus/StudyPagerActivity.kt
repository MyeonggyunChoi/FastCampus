package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_fragment.view.*

class StudyPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_pager)

        val pager = findViewById<ViewPager2>(R.id.pager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        tabLayout.apply {
            this.addTab(this.newTab().setText("first"))
            this.addTab(this.newTab().setText("second"))
        }

//        pager.adapter = FragmentManager(this,2)


        val adapter = ViewAdapter(
            arrayListOf<Int>(1,2),
            LayoutInflater.from(this@StudyPagerActivity)
        )

        pager.adapter = adapter

        TabLayoutMediator(tabLayout,pager) { tab, position ->
            tab.text = position.toString()
        }.attach()
    }
}

var tmpCnt:Int = 0

class ViewAdapter(
    val item:ArrayList<Int>,
    val inflater: LayoutInflater
):RecyclerView.Adapter<ViewAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        lateinit var view:View

        tmpCnt++
        if (tmpCnt%2 == 0) {
            view = inflater.inflate(R.layout.first_fragment,parent,false)
        } else{
            view = inflater.inflate(R.layout.second_fragment,parent,false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return item.size
    }
}

class FragmentManager(
    fragmentActivity: StudyPagerActivity,
    val cnt: Int
):FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return cnt
    }

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return FragmentFirst()
            else -> return  FragmentSecond()
        }
    }
}