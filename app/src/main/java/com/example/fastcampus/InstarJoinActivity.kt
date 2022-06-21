package com.example.fastcampus

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstarJoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_join)

        val sharedPreferences = getSharedPreferences("instagram", Context.MODE_PRIVATE)

        val editTextId = findViewById<EditText>(R.id.editTextId)
        val editTextPw = findViewById<EditText>(R.id.editTextPw)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        findViewById<TextView>(R.id.textViewJoin).setOnClickListener {
            startActivity(Intent(this@InstarJoinActivity, InstarJoinIdActivity::class.java))
        }

        findViewById<TextView>(R.id.textViewLogin).apply {
            this.setOnClickListener {
                if ((editTextId.text.toString() != "" && editTextPw.text.toString() != "")) { // 아이디와 패스워드에 값을 채웠을 때 로그인 기능 실행
                    // 로그인 실행 후 메인 화면으로 이동
                    // 토큰 받아서 공유 정보로 저장 후에 이후에는 바로 로그인 되도록 기능 구현
                    // 계정이 없을 경우 틀렸음을 알리기
                    val user = HashMap<String, Any>()
                    user.put("username", editTextId.text.toString())
                    user.put("password", editTextPw.text.toString())
                    retrofitService.instaLogin(user).enqueue(object : Callback<UserToken> {
                        override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
//                            Log.d("instarr", "" + response.code())
                            when (response.code()) {
                                200 -> {
                                        // 로그인정보 저장
                                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                                        editor.putString("token", response.body()!!.token)
                                        editor.putString("userId",response.body()!!.id.toString())
                                        editor.commit()

                                        moveMainPage()
                                    }
                                else -> {
                                    Toast.makeText(this@InstarJoinActivity, "로그인에 실패하셨습니다.\n계정 정보를 다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<UserToken>, t: Throwable) {
                            Log.d("instarr", "failed")
                        }
                    })
                }
            }
            editTextId.addTextChangedListener(EditWatcher(editTextId, editTextPw, this))
            editTextPw.addTextChangedListener(EditWatcher(editTextId, editTextPw, this))
        }


    }

    inner class EditWatcher(
        val editTextId: EditText,
        val editTextPw: EditText,
        val textViewLogin: TextView
    ) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textViewChangeStatus(editTextId, editTextPw, textViewLogin)
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    fun textViewChangeStatus(editTextId: EditText, editTextPw: EditText, textViewLogin: TextView) {
        if (editTextPw.text.toString() != "" && editTextId.text.toString() != "") {
            textViewLogin.setBackgroundResource(R.drawable.outstar_login)
            textViewLogin.setTextColor(
                ContextCompat.getColor(
                    this@InstarJoinActivity,
                    R.color.white
                )
            )
        } else {
            textViewLogin.setBackgroundResource(R.drawable.outstar_login_invisible)
            textViewLogin.setTextColor(
                ContextCompat.getColor(
                    this@InstarJoinActivity,
                    R.color.white_invisible
                )
            )
        }
    }

    fun moveMainPage() {
        val intent = Intent(this@InstarJoinActivity, InstarMainActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}