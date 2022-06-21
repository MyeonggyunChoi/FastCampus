package com.example.fastcampus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class Assignment_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment1)
    }

    override fun onResume() {
        super.onResume()

        val textDisplay: TextView = findViewById(R.id.textDisplay)
        val buttonOne: Button = findViewById(R.id.buttonOne)
        val buttonTwo: Button = findViewById(R.id.buttonTwo)
        val buttonThree: Button = findViewById(R.id.buttonThree)
        val buttonFour: Button = findViewById(R.id.buttonFour)
        val buttonFive: Button = findViewById(R.id.buttonFive)
        val buttonSix: Button = findViewById(R.id.buttonSix)
        val buttonSeven: Button = findViewById(R.id.buttonSeven)
        val buttonEight: Button = findViewById(R.id.buttonEight)
        val buttonNine: Button = findViewById(R.id.buttonNine)
        val buttonZero: Button = findViewById(R.id.buttonZero)
        val buttonCa: Button = findViewById(R.id.buttonCa)
        val buttonPlus: Button = findViewById(R.id.buttonPlus)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculte)
        
        var preVal: Int = 0

        buttonOne.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "1"
        }
        buttonTwo.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "2"
        }
        buttonThree.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "3"
        }
        buttonFour.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "4"
        }
        buttonFive.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "5"
        }
        buttonSix.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "6"
        }
        buttonSeven.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "7"
        }
        buttonEight.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "8"
        }
        buttonNine.setOnClickListener {
            textDisplay.text = textDisplay.text.toString() + "9"
        }
        buttonZero.setOnClickListener {
            if (textDisplay.text.length != 0) textDisplay.text = textDisplay.text.toString() + "0"
        }
        buttonCa.setOnClickListener {
            preVal = 0
            textDisplay.text = ""
        }
        buttonPlus.setOnClickListener {
            preVal = textDisplay.text.toString().toInt()
            textDisplay.text = ""
        }
        buttonCalculate.setOnClickListener {
            if (preVal != 0 && textDisplay.text != "") {
                textDisplay.text = (preVal + textDisplay.text.toString().toInt()).toString()
                preVal = textDisplay.text.toString().toInt()
            }
        }
    }
}