package com.example.fastcampus

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged

class Assignment_2_solution : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment2_solution)

        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true

        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                // true -> 우리 앱이 주도권을 가져오지 않겠다
                // false -> 우리 앱이 주도권을 가져오겠다
                return false
            }
        })

        try {
            webView.loadUrl(intent.data!!.toString())
        } catch (exception: Exception) {

        }


        var finalUrl: String = ""
        val urlPrefix: String = "http://"
        val address = findViewById<EditText>(R.id.editText)

        // addTextChangeListener 사용법
        address.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                finalUrl = urlPrefix + s.toString()
            }
        })

        // 위의 것을 람다로 처리
        address.doBeforeTextChanged { text, start, count, after -> }
        address.doOnTextChanged { text, start, before, count -> }
        address.doAfterTextChanged {

        }


        val open = findViewById<Button>(R.id.openButton)
        open.setOnClickListener {
//            webView.loadUrl(finalUrl) // -> 쉬운 방법
            Log.d("testt", finalUrl)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
            startActivity(intent)
        }

    }
}