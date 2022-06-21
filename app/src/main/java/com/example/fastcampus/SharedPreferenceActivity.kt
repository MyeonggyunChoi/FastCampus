package com.example.fastcampus

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // SharedPreference 사용법
        findViewById<TextView>(R.id.create).setOnClickListener {
            // Create
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            // MODE_PRIVATE -> 현재 앱에서만 사용하겠다 -> 다른 앱과 공유하지 않겠다. -> 거의 이거만 사용!
            // MODE_WORLD_READBLE : 다른 앱에서도 사용 가능 (읽기만 가능)
            // MODE_WORLD_WRITABLE : 다른 앱에서도 사용 가능 (읽기, 쓰기 가능)
            // MODE_MULTI_PROCESS : 이미 호출되어 사용중인지 체크
            // MODE_APPEND : 기존 preference에 신규로 추가

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("key1", "hello") // key - value 방식으로 데이터를 저장
            editor.putString("key2", "hello2")
            editor.commit()
        }

        findViewById<TextView>(R.id.read).setOnClickListener {
            val sharedPreferences =
                getSharedPreferences("table_name", Context.MODE_PRIVATE) // 생성한 것과 테이블 명 일치해야 함
            val valueOne = sharedPreferences.getString("key1", "Wrong1")
            val valueTwo = sharedPreferences.getString("key2", "Wrong2")
            Log.d("testt", valueOne!!)
            Log.d("testt", valueTwo!!)
        }

        findViewById<TextView>(R.id.update).setOnClickListener {
            val sharedPreferences =
                getSharedPreferences("table_name", Context.MODE_PRIVATE) // 생성한 것과 테이블 명 일치해야 함
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("key1","hello world") // 기존에 사용했던 키와 동일한 키를 사용해서 데이터를 저장 -> Update
            editor.commit()
        }

        findViewById<TextView>(R.id.delete).setOnClickListener{
            val sharedPreferences =
                getSharedPreferences("table_name", Context.MODE_PRIVATE) // 생성한 것과 테이블 명 일치해야 함
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.commit()
        }
    }
}