package com.example.fastcampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import java.text.DecimalFormat

class Assignment3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment3)

        val userList: MutableList<User> = mutableListOf<User>()

        for (i in 0..30) {
            val iPhoneNum:String = DecimalFormat("0000").format(i)
            userList.add(User("" + i + "번째 사람", "010-1111-" + iPhoneNum))
        }

        val container = findViewById<LinearLayoutCompat>(R.id.container)
        val inflater = LayoutInflater.from(this@Assignment3)

        userList.forEach {
            val userView = inflater.inflate(R.layout.user_item,null)
            val image = userView.findViewById<ImageView>(R.id.userImage)
            val userName = userView.findViewById<TextView>(R.id.userName)
            val userPhoneNumber = userView.findViewById<TextView>(R.id.userPhoneNum)

            image.setImageDrawable(resources.getDrawable(R.drawable.arrows,null))
            userName.text = it.name
            userPhoneNumber.text = it.phonNumber

            userView.setOnClickListener{
//                val intent = Intent(this@Assignment3,Assignment3Intent::class.java)
//                intent.putExtra("name",userName.text)
//                intent.putExtra("phoneNum",userPhoneNumber.text)
//                startActivity(intent)
                // 아래 방법이 더 간결할 듯
                startActivity(Intent(this@Assignment3,Assignment3Intent::class.java).apply {
                    this.putExtra("name",userName.text)
                    this.putExtra("phoneNum",userPhoneNumber.text)
                })
            }

            container.addView(userView)
        }
    }
}

class User(val name: String, val phonNumber: String)