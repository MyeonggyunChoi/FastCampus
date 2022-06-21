package com.example.fastcampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ApplicationActivityOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_one)

        (findViewById<TextView>(R.id.changeActivity)).setOnClickListener{
            startActivity(
                Intent(this@ApplicationActivityOne,ApplicationActivityTwo::class.java)
            )
        }

//        (findViewById<TextView>(R.id.testMethod)).setOnClickListener{
//            (applicationContext as MasterApplication).methodFromApplication()
//            val userId:Int = (applicationContext as MasterApplication).userId
//            Log.d("testtt",""+userId)
//        }
    }
}