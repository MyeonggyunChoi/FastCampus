package com.example.fastcampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstarJoinConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_join_confirm)
        val getIntet = intent
        val userId = intent.extras!!.getString("id")
        val userPw = intent.extras!!.getString("pw")
        val userName = intent.extras!!.getString("name")

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        findViewById<TextView>(R.id.textViewIdConfirm).text = userId + " 님으로"

        findViewById<TextView>(R.id.TextViewNextMove).setOnClickListener {
            // 회원가입 처리
            val user = HashMap<String, Any>()
            user.put("username", userId.toString())
            user.put("password1", userPw.toString())
            user.put("password2", userPw.toString())

            retrofitService.instaJoin(user).enqueue(object : Callback<UserToken> {
                override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                    when (response.code()) {
                        200 -> {
                            val intent = Intent(
                                this@InstarJoinConfirmActivity,
                                InstarJoinActivity::class.java
                            )
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            startActivity(intent)
                        }
                        else -> Log.d("instarr",""+response.code()+" : "+response.message())
                    }

                }

                override fun onFailure(call: Call<UserToken>, t: Throwable) {
                    Toast.makeText(
                        this@InstarJoinConfirmActivity,
                        "회원가입에 실패하셨습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })


        }
    }
}