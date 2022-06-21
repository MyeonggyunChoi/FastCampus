package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        val currentThread = Thread.currentThread()
        Log.d("testt", "" + currentThread)


//        Thread {
//            for (a in 1..100) {
//                Log.d("testt", "a " + a)
//            }
//        }.start()
//
//        Thread {
//            for (b in 1..100) {
//                Log.d("testt", "b " + b)
//            }
//        }.start()

        Thread {
            val currentThread = Thread.currentThread()
            Log.d("testt", "A" + currentThread)
            findViewById<TextView>(R.id.test).text = "gkflnlag"
            // UI 관련 작업을 메인 쓰레드가 아닌 쓰레드에서 하려고 하면 해당 작업은 메인쓰레드의 queue로 들어간다.
            // -> 에러가 발생하지 않을 수도 있다.

            // 위 문제를 안전하게 처리하는 방법
            runOnUiThread{
                findViewById<TextView>(R.id.test).text = "safe"
            }
        }.start()

    }
}