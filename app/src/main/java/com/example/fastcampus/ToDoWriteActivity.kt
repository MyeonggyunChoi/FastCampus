package com.example.fastcampus

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ToDoWriteActivity : AppCompatActivity() {
    lateinit var contentEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_write)

        contentEditText = findViewById(R.id.content_edit_text)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        findViewById<TextView>(R.id.make_todo).setOnClickListener {
            val body = HashMap<String, Any>()
            body.put("is_complete", "False")
            body.put("content", contentEditText.text)

            val header = HashMap<String, String>()
            val sp = this.getSharedPreferences(
                "instagram",
                Context.MODE_PRIVATE
            )
            val token = sp.getString("token", "")
            header.put("Authorization", "token " + token!!)

            retrofitService.makeToDo(header, body).enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {

                }

                override fun onFailure(call: Call<Any>, t: Throwable) {

                }
            })
        }

    }


}