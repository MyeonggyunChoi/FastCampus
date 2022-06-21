package com.example.fastcampus

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.fastcampus.databinding.ItemListBinding

val binding = ItemListBinding.inflate(LayoutInflater.from(getC))

class MyRecyclerViewAdapter(private val items: ArrayList<String>):RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {
    inner class MyViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(title:String) {
            // 제목달기
            binding.title = title

            //서브 메뉴 달기
            binding.textViewOptions.setOnClickListener {
                val popup = PopupMenu(binding.textViewOptions.context, binding.textViewOptions)
                popup.inflate(R.menu.recyclerview_item_menu)
                popup.setOnMenuItemClickListener { item ->
                    val str = when(item.itemId) {
                        R.id.itemPlayPause -> "나중에 볼 동영상에 저장"
                        else -> "오류"
                    }

                    Toast.makeText(binding.textViewOptions.context, str, Toast.LENGTH_SHORT).show()
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}