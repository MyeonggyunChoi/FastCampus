package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class StudyFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_fragment)

        val fragmentManager = supportFragmentManager
        val fragmentFirst = FragmentFirst()

        findViewById<TextView>(R.id.getFragment).setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString("key","val")
            fragmentFirst.arguments = bundle

            transaction.replace(R.id.root, fragmentFirst, "tag")
            transaction.commit()
        }

        findViewById<TextView>(R.id.removeFragment).setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(fragmentFirst)
            transaction.commit()
        }
    }
    fun printTestLog(){
        Log.d("testt","print test log ")
    }
}