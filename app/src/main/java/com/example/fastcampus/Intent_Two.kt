package com.example.fastcampus

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class Intent_Two : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_two)

        // Intent_Ont -> Intent_Two
        val intent = intent // 우항의 인텐트는 나를 호출한 엑티비티에서 선언한 인텐트
        val data: String? = intent.extras?.getString("extra-data")
        if (data != null) {
            Log.d("dataa", data)
        }

        (findViewById<TextView>(R.id.finish)).apply {
            this.setOnClickListener {
                val intent: Intent = Intent()
                intent.putExtra("result", "success")
                setResult(RESULT_OK, intent) // -> Intent_One으로 돌아갈 때, 값을 보냄
                // Intent_One -> Intent_Two 이니 Intent_Two를 종료시키면 됨
                finish()
            }
        }

        val imageView = findViewById<ImageView>(R.id.imageView)
        val uri = Uri.parse(
            intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM).toString()
        )
        imageView.setImageURI(uri)
    }
}