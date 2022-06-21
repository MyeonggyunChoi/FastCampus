package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class ViewControl_02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_control02)

        // 뷰를 코틀린 파일(Activity)로 가져오는 방법
        val textViewOne: TextView = findViewById(R.id.textViewOne)
        val buttonOne: Button = findViewById(R.id.buttonOne)
        Log.d("testt", textViewOne.text.toString())

        // Listener 사용 방법
        // 람다 버전
        buttonOne.setOnClickListener {
            // 버튼이 클릭되었을 때 동작할 코드
            Log.d("testt", "Click!")
        }

        // 아래 내용은 참고하고 람다 버전을 주로 사용함
        // 현재는 잘 사용하지 않는 풀 버전
        val clickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("testt", "Click!!")
            }
        }
        buttonOne.setOnClickListener(clickListener)

        // 축약 버전 1 (익명함수)
        buttonOne.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("testt", "Click!!!")
            }
        })
    }
}