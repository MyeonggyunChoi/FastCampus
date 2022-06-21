// 강의랑 재플린 파일이랑 달라서 실제로 저장은 못하는 부분ㅠㅠ
// 그냥 id를 name으로 써버림!
package com.example.fastcampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class InstarJoinNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instar_join_name)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val getIntet = intent

        findViewById<TextView>(R.id.TextViewNextMove).apply {
            this.setOnClickListener {
                if (editTextName.text.toString() != "") {
                    val intent =
                        Intent(this@InstarJoinNameActivity, InstarJoinConfirmActivity::class.java)
                    intent.putExtra("id", getIntet.extras!!.getString("id"))
                    intent.putExtra("pw", getIntet.extras!!.getString("pw"))
                    intent.putExtra("name", editTextName.text.toString())
                    startActivity(intent)
                }
            }

            editTextName.addTextChangedListener(EditWatcher(editTextName, this))

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
                    this@InstarJoinNameActivity,
                    R.color.white
                )
            )
        } else {
            textViewNextMove.setBackgroundResource(R.drawable.outstar_login_invisible)
            textViewNextMove.setTextColor(
                ContextCompat.getColor(
                    this@InstarJoinNameActivity,
                    R.color.white_invisible
                )
            )
        }
    }
}