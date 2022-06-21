package com.example.fastcampus

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

// NetworkOnMainThreadException
// - 네트워크 관련 작업을 메인쓰레드를 실행하면 안됨

class NerworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nerwork)

        NetworkAsyncTask().execute()
    }
}

class NetworkAsyncTask() : AsyncTask<Any?, Any?, Any?>(){
    override fun doInBackground(vararg params: Any?): Any? {
        val urlString: String = "http://mellowcode.org/json/students"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection // 서버와 연결

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")

        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader( // 서버가 보낸 값을 사람이 알아볼 수 있게 바꿔줌
                    connection.inputStream, // 서버가 보낸 값이 저장됨
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("testt",buffer)
        }
        return null
    }
}