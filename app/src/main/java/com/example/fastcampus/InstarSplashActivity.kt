package com.example.fastcampus

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InstarSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_splash)

        val sharedPreferences = getSharedPreferences("instagram", Context.MODE_PRIVATE)
        if (sharedPreferences.getString("token", "") != "") {
            val intent = Intent(this@InstarSplashActivity, InstarMainActivity::class.java)
            startActivity(intent)
        } else{
            val intent = Intent(this@InstarSplashActivity, InstarJoinActivity::class.java)
            startActivity(intent)
        }
    }
}