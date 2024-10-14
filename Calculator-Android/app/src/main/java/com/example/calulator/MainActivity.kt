package com.example.calulator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnPlus).setOnClickListener(this)
        findViewById<Button>(R.id.btnMinus).setOnClickListener(this)
        findViewById<Button>(R.id.btnMultiple).setOnClickListener(this)
        findViewById<Button>(R.id.btnDivide).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
        findViewById<Button>(R.id.btnChangeSign).setOnClickListener(this)
        findViewById<Button>(R.id.btnDelete).setOnClickListener(this)
        findViewById<Button>(R.id.btnClear).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)
            R.id.btnPlus -> {
                if (state == 1) {
                    op = 1 // Addition
                    state = 2
                } else {
                    val result = op1 + op2
                    textResult.text = "$result"
                    state = 1
                    op1 = result
                    op2 = 0
                    op = 0
                }
            }
            R.id.btnMinus -> {
                if (state == 1) {
                    op = 2 // Subtraction
                    state = 2
                } else {
                    val result = op1 - op2
                    textResult.text = "$result"
                    state = 1
                    op1 = result
                    op2 = 0
                    op = 0
                }
            }
            R.id.btnMultiple -> {
                if (state == 1) {
                    op = 3 // Multiplication
                    state = 2
                } else {
                    val result = op1 * op2
                    textResult.text = "$result"
                    state = 1
                    op1 = result
                    op2 = 0
                    op = 0
                }
            }
            R.id.btnDivide -> {
                if (state == 1) {
                    op = 4 // Division
                    state = 2
                } else {
                    val result = if (op2 != 0) op1 / op2 else 0 // Handle division by zero
                    textResult.text = "$result"
                    state = 1
                    op1 = result
                    op2 = 0
                    op = 0
                }
            }
            R.id.btnEqual -> {
                val result = when (op) {
                    1 -> op1 + op2
                    2 -> op1 - op2
                    3 -> op1 * op2
                    4 -> if (op2 != 0) op1 / op2 else 0 // Handle division by zero
                    else -> 0
                }
                textResult.text = "$result"
                state = 1
                op1 = result
                op2 = 0
                op = 0
            }
            R.id.btnDelete -> {
                // Delete current number
                if (state == 1) {
                    op1 = 0
                    textResult.text = "$op1"
                } else {
                    op2 = 0
                    textResult.text = "$op2"
                }
            }
            R.id.btnClear -> {
                // Clear everything
                op1 = 0
                op2 = 0
                op = 0
                state = 1
                textResult.text = "0"
            }
            R.id.btnChangeSign -> {
                if (state == 1) {
                    op1 = -op1
                    textResult.text = "$op1"
                } else {
                    op2 = -op2
                    textResult.text = "$op2"
                }
            }
        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            textResult.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            textResult.text = "$op2"
        }
    }
}