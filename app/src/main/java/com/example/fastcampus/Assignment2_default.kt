package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class Assignment2_default : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var webView: WebView
    var webAdrress: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment2_default)

        getElement()

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.settings.javaScriptEnabled = true
    }

    override fun onResume() {
        super.onResume()

        button.setOnClickListener {
            if (editText.text.toString() != "") {
                webView.loadUrl(editText.text.toString())
            }

        }
    }

    fun getElement(): Unit {
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.openButton)
        webView = findViewById(R.id.webView)
    }
}