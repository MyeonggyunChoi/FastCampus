package com.example.fastcampus

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class Intent_One : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_one)

        // 암시적 인텐트
        // - 전화, SMS, Google Play Store, Website, GoogleMap, 사진첩 등등
        // - 상수
        //      - 변하지 않는 값
        //      - 모두 대문자로 표시하는 관례가 있음
        // - URI (Uniform Resource Identifier)
        //      - ID라고 생각하면 됨 --> 고유하다
        //      - 자원을 나타내는 고유한 주소
        val implicit_intent: TextView = findViewById(R.id.implicit_intent)
        implicit_intent.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "010-5361-5053"))
            startActivity(intent)
        }

        // 명시적 인텐트 + ComponentName -> 액티비티 전환
        // 사용하지 않는 것 추천! 이 다음 방법 추천
        val intent_one: TextView = findViewById(R.id.intent_one)
        intent_one.setOnClickListener {
            val intent: Intent = Intent()
            val componentName: ComponentName = ComponentName(
                "com.example.fastcampus",
                "com.example.fastcampus.Intent_Two"
            )
            intent.component = componentName
            startActivity(intent)
        }

        // 명시적 인텐트 -> 엑티비티 전
        // 해당 방법 추명
        // Context
        // - 문맥
        // A 엑티비티 -> B 엑티비티
        (findViewById<TextView>(R.id.intent_two)).apply {
            this.setOnClickListener {
                startActivity(
                    Intent(this@Intent_One, Intent_Two::class.java) // 인텐트를 바로 만들어서 실행
                )
            }
        }

        // 명시적 인텐트 + data 전달
        (findViewById<TextView>(R.id.intent_three)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                intent.putExtra("extra-data", "data-one")
                startActivity(intent)
            }
        }

        // 명시적 인텐트 + 결과 받기
        // requestCode
        // - 구분을 하기 위해서
        //      - ex)
        //      - Intent_One -> Intent_Two (request 1)
        //      - Intent_One -> Intent_Three (request 2)
        //      - Intent_One -> Intent_Four (request 3)
        (findViewById<TextView>(R.id.intent_four)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                startActivityForResult(intent, 1) //deprecated 됨
            }
        }

        // 명시적 인텐트 + 결과 받기 (ActivityResult API)
        // -> requestCode 존재 X
        // -> requestCode 없이도 요청자를 구분할 수 있다.
        val startActivityLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            // onActivityResult에 해당하는 부분
            when (it.resultCode) {
                RESULT_OK -> {
                    Log.d("dataa", it.data?.extras?.getString("result")!!)
                }
                // onActivityResult
                // - 모든 intent가 한 곳에서 처리됨 -> 구분이 필요하다 (request code)
                // ActivityResult API
                // - 각각의 intent가 처리되는 곳이 별도로 있음 -> 구분이 필요 없다.
            }

        }
        (findViewById<TextView>(R.id.intent_five)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java)
                startActivityLauncher.launch(intent)
            }
        }

        // 명시적 인텐트 + 이미지 URI 전달 -> 이런 게 있다 정도로 넘겨도 괜찮음
        (findViewById<TextView>(R.id.intent_six)).apply {
            this.setOnClickListener {
                val intent = Intent(this@Intent_One, Intent_Two::class.java).apply {
                    val imageUri =
                        Uri.parse("android.resource://" + packageName + "/drawable/" + "arrows")
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_STREAM, imageUri)
                    this.setType("image/*")
                }
                startActivityLauncher.launch(intent)
            }
        }

        // 우리가 알아야 할 것
        // 인텐트를 이용해서 데이터 전달이 가능하다
        //  - 인텐트를 이용해서 키 벨류 데이터를 전달한다.
        //  - 인텐트를 이용해서 이미지를 전달한다.
        //  ...
    }

    // startActivityForResult에서 값을 받아오는 부분
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // resultCode (status code)
        // - 최종 결과
        // - ex) 성공, 실패
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1 -> {
                when (resultCode) {
                    RESULT_OK -> {
                        val data: String? = data?.extras?.getString("result")
                        Log.d("dataa", data!!)
                    }
                }
            }
        }
    }
}
