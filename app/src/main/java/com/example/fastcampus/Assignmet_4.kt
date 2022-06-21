package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Assignmet_4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignmet4)

        var chattingList = mutableListOf<Chatting>()

        for (i in 0..10) {
            chattingList.add(Chatting(1, "message : a" + i))
            chattingList.add(Chatting(1, "message : a" + i + 10))
            chattingList.add(Chatting(2, "message : b" + i))
        }


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = RecyclerViewAdapt(chattingList, LayoutInflater.from(this))
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}

class RecyclerViewAdapt(
    var chattingList: MutableList<Chatting>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var message: TextView

        init {
            if (viewType == 1) {
                message = itemView.findViewById(R.id.messageA)
            } else {
                message = itemView.findViewById(R.id.messageB)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            val view = inflater.inflate(R.layout.chatting_item_a, parent, false)
            return ViewHolder(view, viewType)
        } else {
            val view = inflater.inflate(R.layout.chatting_item_b, parent, false)
            return ViewHolder(view, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.message.text = chattingList.get(position).message
        }
    }

    override fun getItemCount(): Int {
        return chattingList.size
    }

    override fun getItemViewType(position: Int): Int {
        return chattingList.get(position).sender
    }
}

class Chatting(var sender: Int, var message: String)