package com.example.fastcampus

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class ResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        (findViewById<TextView>(R.id.text)).setOnClickListener{
            (it as TextView).text = resources.getText(R.string.app_name)
            it.background = resources.getDrawable(R.drawable.arrows,null) //테마를 넣고 싶으면 null 대신 this.theme 를 넣으면 됨
            it.background = ContextCompat.getDrawable(this, R.drawable.back)
            it.background = ResourcesCompat.getDrawable(resources,R.drawable.btn_outsta_home,null)
        }
    }
}