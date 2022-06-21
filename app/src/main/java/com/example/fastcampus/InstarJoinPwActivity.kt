package com.example.fastcampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class InstarJoinPwActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_join_pw)
        val editTextPw = findViewById<EditText>(R.id.editTextPw)
        val getIntet = intent

        findViewById<TextView>(R.id.TextViewNextMove).apply {
            this.setOnClickListener {
                if(editTextPw.text.toString() != ""){
                    val intent = Intent(this@InstarJoinPwActivity, InstarJoinNameActivity::class.java)
                    intent.putExtra("id",getIntet.extras!!.getString("id"))
                    intent.putExtra("pw",editTextPw.text.toString())
                    startActivity(intent)
                }
            }

            editTextPw.addTextChangedListener(EditWatcher(editTextPw, this))
        }
    }

    inner class EditWatcher(
        val editText: EditText,
        val textViewNextMove: TextView
    ) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textViewChangeStatus(editText, textViewNextMove)
        }

        override fun afterTextChanged(s: Editable?) {

        }
    }

    fun textViewChangeStatus(editText: EditText, textViewNextMove: TextView) {
        if (editText.text.toString() != "") {
            textViewNextMove.setBackgroundResource(R.drawable.outstar_login)
            textViewNextMove.setTextColor(
                ContextCompat.getColor(
                    this@InstarJoinPwActivity,
                    R.color.white
                )
            )
        } else {
            textViewNextMove.setBackgroundResource(R.drawable.outstar_login_invisible)
            textViewNextMove.setTextColor(
                ContextCompat.getColor(
                    this@InstarJoinPwActivity,
                    R.color.white_invisible
                )
            )
        }
    }
}