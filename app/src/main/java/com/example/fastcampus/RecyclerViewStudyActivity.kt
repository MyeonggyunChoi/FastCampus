package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewStudyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_study)

        val itemList:ArrayList<String> = arrayListOf("a","b","c","a","b","c","a","b","c")
        val adapter = RecyclerViewAdapter1(
            itemList,
            LayoutInflater.from(this@RecyclerViewStudyActivity)
        )
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
    }
}

class RecyclerViewAdapter1(
    val itemList:ArrayList<String>,
    val inflater: LayoutInflater
):RecyclerView.Adapter<RecyclerViewAdapter1.ViewHolder>(){
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textView:TextView

        init {
            textView = itemView.findViewById(R.id.study_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.study_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = itemList.get(position)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}