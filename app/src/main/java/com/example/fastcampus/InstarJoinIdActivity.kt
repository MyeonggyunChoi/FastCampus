package com.example.fastcampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class InstarJoinIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_join_id)
        val editTextId = findViewById<EditText>(R.id.editTextId)


        findViewById<TextView>(R.id.TextViewNextMove).apply {
            this.setOnClickListener {
                if (editTextId.text.toString() != "") {
                    val intent = Intent(this@InstarJoinIdActivity, InstarJoinPwActivity::class.java)
                    intent.putExtra("id", editTextId.text.toString())
                    startActivity(intent)
                }
            }

            editTextId.addTextChangedListener(EditWatcher(editTextId, this))

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
                    this@InstarJoinIdActivity,
                    R.color.white
                )
            )
        } else {
            textViewNextMove.setBackgroundResource(R.drawable.outstar_login_invisible)
            textViewNextMove.setTextColor(
                ContextCompat.getColor(
                    this@InstarJoinIdActivity,
                    R.color.white_invisible
                )
            )
        }
    }
}