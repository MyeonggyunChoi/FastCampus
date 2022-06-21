package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Assignment3Intent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment3_intent)

        val intent = intent
        (findViewById<TextView>(R.id.userName)).text = intent.getStringExtra("name") ?: ""
        (findViewById<TextView>(R.id.userPhoneNum)).text = intent.getStringExtra("phoneNum") ?: ""
    }
}