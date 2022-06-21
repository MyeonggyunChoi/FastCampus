package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RecyclerViewExtraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_extra)
    }
}

// 리사이클러뷰에 스와이프, 드래그 기능 달기
//val swipeHelperCallback = SwipeHelperCallback