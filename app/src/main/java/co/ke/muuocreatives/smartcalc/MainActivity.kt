package co.ke.muuocreatives.smartcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import co.ke.muuocreatives.smatcalc.R
import java.math.BigDecimal


class MainActivity : AppCompatActivity() {

    enum class Operator {
        NONE, ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    // Define variables for UI elements

    lateinit var tvInput: TextView
    lateinit var tvOldInput: TextView
    lateinit var tvCurrentOperand: TextView
    lateinit var btnOne: Button
    lateinit var btnTwo: Button
    lateinit var btnThree: Button
    lateinit var btnFour: Button
    lateinit var btnFive: Button
    lateinit var btnSix: Button
    lateinit var btnSeven: Button
    lateinit var btnEight: Button
    lateinit var btnNine: Button
    lateinit var btnZero: Button

    lateinit var btnDot: Button
    lateinit var btnEqual: Button
    lateinit var btnDivide: Button
    lateinit var btnSubtract: Button
    lateinit var btnAdd: Button
    lateinit var btnMultiply: Button

    lateinit var btnBackspace: ImageButton
    lateinit var btnClear: Button
    lateinit var btnAllclear: Button

    // Initialize currentInput as a StringBuilder
    var currentInput = StringBuilder()
    var currentOperator = Operator.NONE
    var operand1: BigDecimal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Define variables for UI elements
        tvInput = findViewById<TextView>(R.id.tvInput)
        tvOldInput = findViewById<TextView>(R.id.tvOldInput)
        tvCurrentOperand = findViewById<TextView>(R.id.tvCurrentOperand)
        btnOne = findViewById<Button>(R.id.btnOne)
        btnTwo = findViewById<Button>(R.id.btnTwo)
        btnThree = findViewById<Button>(R.id.btnThree)
        btnFour = findViewById<Button>(R.id.btnFour)
        btnFive = findViewById<Button>(R.id.btnFive)
        btnSix = findViewById<Button>(R.id.btnSix)
        btnSeven = findViewById<Button>(R.id.btnSeven)
        btnEight = findViewById<Button>(R.id.btnEight)
        btnNine = findViewById<Button>(R.id.btnNine)
        btnZero = findViewById<Button>(R.id.btnZero)

        btnDot = findViewById<Button>(R.id.btnDot)
        btnEqual = findViewById<Button>(R.id.btnEqual)
        btnDivide = findViewById<Button>(R.id.btnDivide)
        btnSubtract = findViewById<Button>(R.id.btnSubtract)
        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnMultiply = findViewById<Button>(R.id.btnMultiply)

        btnBackspace = findViewById<ImageButton>(R.id.btnBackspace)
        btnClear = findViewById<Button>(R.id.clear)
        btnAllclear = findViewById<Button>(R.id.allClear)

        // Set click listeners for number buttons
        btnOne.setOnClickListener { appendNumber("1") }
        btnTwo.setOnClickListener { appendNumber("2") }
        btnThree.setOnClickListener { appendNumber("3") }
        btnFour.setOnClickListener { appendNumber("4") }
        btnFive.setOnClickListener { appendNumber("5") }
        btnSix.setOnClickListener { appendNumber("6") }
        btnSeven.setOnClickListener { appendNumber("7") }
        btnEight.setOnClickListener { appendNumber("8") }
        btnNine.setOnClickListener { appendNumber("9") }
        btnZero.setOnClickListener { appendNumber("0") }
        btnDot.setOnClickListener { appendNumber(".") }
        // Set click listeners for operator buttons
        btnAdd.setOnClickListener { setOperator(Operator.ADD) }
        btnSubtract.setOnClickListener { setOperator(Operator.SUBTRACT) }
        btnMultiply.setOnClickListener { setOperator(Operator.MULTIPLY) }
        btnDivide.setOnClickListener { setOperator(Operator.DIVIDE) }

        // Handle equals button click
        btnEqual.setOnClickListener { calculateResult() }

        // Handle clear button click
        btnClear.setOnClickListener { clearInput() }
        btnAllclear.setOnClickListener { allClearInput() }

        //backSpace button
        btnBackspace.setOnClickListener { handleBackspace() }
    }

    private fun appendNumber(number: String) {
        currentInput.append(number)
        updateDisplay()
    }
    private fun handleBackspace() {
        if (currentInput.isNotEmpty()) {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
        }
    }

    private fun setOperator(operator: Operator) {
        if (operand1 == null) {
            operand1 = BigDecimal(currentInput.toString())
            currentInput.clear()
        }
        tvOldInput.text = operand1.toString()
        tvInput.text = ""
        currentOperator = operator
        tvCurrentOperand.text = operatorToString(operator)
    }
    private fun operatorToString(operator: Operator): String {
        return when (operator) {
            Operator.ADD -> "+"
            Operator.SUBTRACT -> "-"
            Operator.MULTIPLY -> "×"
            Operator.DIVIDE -> "÷"
            Operator.NONE -> ""
        }
    }
    private fun calculateResult() {
        val operand2 = BigDecimal(currentInput.toString())

            var result: BigDecimal? = null
            Log.d("SmartCalc", "operand1= ${operand1}")
            Log.d("SmartCalc", "operand2= ${operand2}")
            Log.d("SmartCalc", "currentInput= ${currentInput.toString()}")

            when (currentOperator) {
                Operator.ADD -> result = operand1?.add(operand2)
                Operator.SUBTRACT -> result = operand1?.subtract(operand2)
                Operator.MULTIPLY -> result = operand1?.multiply(operand2)
                Operator.DIVIDE -> {
                    if (operand2 != BigDecimal.ZERO) {
                        result = operand1?.divide(operand2, 10, BigDecimal.ROUND_HALF_UP)
                    }
                }
                Operator.NONE -> result = operand2
            }

            // Display the result and reset the state
            if (result != null) {
                tvOldInput.text = ("${operand1}${operatorToString(currentOperator)}${operand2}").toString()
                tvInput.text = result.toString()
                operand1 = result
            }

    }
    private fun allClearInput() {
        currentInput.clear()
        operand1 = null
        currentOperator = Operator.NONE
        tvOldInput.text = ""
        tvInput.text = "0"
        tvCurrentOperand.text = ""
    }
    private fun clearInput() {
        currentInput.clear()
        currentOperator = Operator.NONE
        tvInput.text = "0"
        operand1 = null
    }

    private fun updateDisplay() {
        tvInput.text = currentInput.toString()
    }
}