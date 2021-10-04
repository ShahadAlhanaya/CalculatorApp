package com.example.calculatorapp

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityCL: ConstraintLayout
    private lateinit var calculation: TextView
    private lateinit var zero: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var multiplication: Button
    private lateinit var division: Button
    private lateinit var addition: Button
    private lateinit var subtraction: Button
    private lateinit var modular: Button
    private lateinit var equal: Button
    private lateinit var sign: Button
    private lateinit var decimal: Button
    private lateinit var ac: Button
    private lateinit var del: Button

    var currentCalculation = "0"
    var result = 0f
    var operand1 = ""
    var operand2 = ""
    var operator = ' '


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityCL = findViewById(R.id.mainActivity)

        zero = findViewById(R.id.btn_0)
        one = findViewById(R.id.btn_1)
        two = findViewById(R.id.btn_2)
        three = findViewById(R.id.btn_3)
        four = findViewById(R.id.btn_4)
        five = findViewById(R.id.btn_5)
        six = findViewById(R.id.btn_6)
        seven = findViewById(R.id.btn_7)
        eight = findViewById(R.id.btn_8)
        nine = findViewById(R.id.btn_9)

        calculation = findViewById(R.id.calculation)
        multiplication = findViewById(R.id.btn_multiplication)
        division = findViewById(R.id.btn_division)
        addition = findViewById(R.id.btn_addition)
        subtraction = findViewById(R.id.btn_subtraction)
        modular = findViewById(R.id.btn_modular)

        equal = findViewById(R.id.btn_equal)
        sign = findViewById(R.id.btn_sign)
        decimal = findViewById(R.id.btn_decimal)
        ac = findViewById(R.id.btn_ac)
        del = findViewById(R.id.btn_del)

        zero.setOnClickListener { numberInput('0') }
        one.setOnClickListener { numberInput('1') }
        two.setOnClickListener { numberInput('2') }
        three.setOnClickListener { numberInput('3') }
        four.setOnClickListener { numberInput('4') }
        five.setOnClickListener { numberInput('5') }
        six.setOnClickListener { numberInput('6') }
        seven.setOnClickListener { numberInput('7') }
        eight.setOnClickListener { numberInput('8') }
        nine.setOnClickListener { numberInput('9') }


        addition.setOnClickListener { operationInput('+') }
        subtraction.setOnClickListener { operationInput('-') }
        multiplication.setOnClickListener { operationInput('*') }
        division.setOnClickListener { operationInput('/') }
        modular.setOnClickListener { operationInput('%') }


        equal.setOnClickListener {
            if (operator != ' ') {
                if (calculate()) {
                    operand1 = "$result"
                    currentCalculation = "$operand1"
                    calculation.text = "$operand1"

                }
            }
        }

        ac.setOnClickListener { clear() }

        decimal.setOnClickListener { decimalInput() }

    }

    private fun operationInput(ope: Char) {
        if (operand1 != "") {
            if (operand2 != "") {
                if (calculate()) {
                    operand1 = "$result"
                    currentCalculation = "$operand1"
                    calculation.text = "$operand1"

                }
            }
            if (ope == '+') {
                operator = '+'
                currentCalculation = "$operand1 $operator"
                calculation.text = currentCalculation
            }
            if (ope == '*') {
                operator = '*'
                currentCalculation = "$operand1 $operator"
                calculation.text = currentCalculation
            }
            if (ope == '-') {
                operator = '-'
                currentCalculation = "$operand1 $operator"
                calculation.text = currentCalculation
            }
            if (ope == '/') {
                operator = '/'
                currentCalculation = "$operand1 $operator"
                calculation.text = currentCalculation
            }
            if (ope == '%') {
                operator = '%'
                currentCalculation = "$operand1 $operator"
                calculation.text = currentCalculation
            }
        }
    }

    private fun decimalInput() {
        if (operator == ' ') {
            if (!operand1.contains('.')) {
                operand1 += '.'
            }
            currentCalculation = "$operand1"
            calculation.text = currentCalculation
        } else {
            if (!operand2.contains('.')) {
                operand2 += '.'
            }
            currentCalculation = "$operand1 $operator $operand2"
            calculation.text = currentCalculation
        }
    }

    private fun numberInput(num: Char) {
        if (operator == ' ') {
            if (operand1 == "0") {
                operand1 = "$num"
            } else {
                operand1 += num
            }
            currentCalculation = "$operand1"
            calculation.text = currentCalculation

        } else {
            if (operand2 == "0") {
                operand2 = "$num"
            } else {
                operand2 += num
            }
            currentCalculation = "$operand1 $operator $operand2"
            calculation.text = currentCalculation
        }
    }

    private fun calculate(): Boolean {
        if (operator == '/' && operand2 == "0") {
            Snackbar.make(mainActivityCL, "Cannot divide by zero", Snackbar.LENGTH_SHORT).show()
//            Toast.makeText(this,"Cannot divide by zero", Toast.LENGTH_SHORT).show()
            return false
        }
        result = when (operator) {
            '+' -> operand1.toFloat() + operand2.toFloat()
            '-' -> operand1.toFloat() - operand2.toFloat()
            '*' -> operand1.toFloat() * operand2.toFloat()
            '%' -> operand1.toFloat() % operand2.toFloat()
            '/' -> operand1.toFloat() / operand2.toFloat()
            else -> 0f
        }
        currentCalculation = "0"
        operand1 = ""
        operand2 = ""
        operator = ' '
        return true
    }

    private fun clear() {
        currentCalculation = "0"
        result = 0f
        operand1 = ""
        operand2 = ""
        operator = ' '
        calculation.text = currentCalculation
    }

    override fun onConfigurationChanged(newConfig: Configuration) {

        // there is an issue with calling this method, working on solving it
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            calculation.setPadding(0, 0, 24, 0)
            calculation.textSize = 24f
            calculation.setTextColor(Color.GREEN)
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            calculation.setPadding(0, 24, 24, 0)
            calculation.textSize = 32f
            calculation.setTextColor(Color.BLUE)

        }
    }
}