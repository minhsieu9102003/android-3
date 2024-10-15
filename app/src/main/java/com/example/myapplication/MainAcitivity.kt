package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var displayTextView: TextView
    private var operand1: Int? = null
    private var pendingOperation = "="
    private var isNewOperand = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayTextView = findViewById(R.id.displayTextView)


        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)


        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonDelete: Button = findViewById(R.id.buttonDelete)


        val numberButtons = listOf(
            button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9
        )

        for (button in numberButtons) {
            button.setOnClickListener { numberPressed(button.text.toString()) }
        }


        buttonAdd.setOnClickListener { operationPressed("+") }
        buttonSubtract.setOnClickListener { operationPressed("−") }
        buttonMultiply.setOnClickListener { operationPressed("×") }
        buttonDivide.setOnClickListener { operationPressed("÷") }
        buttonEquals.setOnClickListener { operationPressed("=") }


        buttonClear.setOnClickListener {
            displayTextView.text = "0"
            operand1 = null
            pendingOperation = "="
            isNewOperand = true
        }


        buttonDelete.setOnClickListener {
            val text = displayTextView.text.toString()
            if (text.isNotEmpty() && text != "0") {
                displayTextView.text = text.dropLast(1)
                if (displayTextView.text.isEmpty()) {
                    displayTextView.text = "0"
                    isNewOperand = true
                }
            }
        }
    }

    private fun numberPressed(digit: String) {
        if (isNewOperand || displayTextView.text == "0") {
            displayTextView.text = digit
            isNewOperand = false
        } else {
            displayTextView.append(digit)
        }
    }

    private fun operationPressed(operation: String) {
        val value = displayTextView.text.toString()
        try {
            val number = value.toInt()
            performOperation(number, operation)
        } catch (e: NumberFormatException) {
            displayTextView.text = ""
        }
        pendingOperation = operation
        isNewOperand = true
    }

    private fun performOperation(value: Int, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            when (pendingOperation) {
                "=" -> operand1 = value
                "+" -> operand1 = operand1!! + value
                "−" -> operand1 = operand1!! - value
                "×" -> operand1 = operand1!! * value
                "÷" -> operand1 = if (value == 0) {
                    null
                } else {
                    operand1!! / value
                }
            }
        }
        displayTextView.text = operand1?.toString() ?: "Error"
    }
}
