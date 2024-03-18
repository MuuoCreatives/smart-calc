package co.ke.muuocreatives.smatcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    // Define variables for UI elements
    val textInput = findViewById<TextView>(R.id.tvInput)
    val btnOne = findViewById<Button>(R.id.btnOne)
    val btnTwo = findViewById<Button>(R.id.btnTwo)
    val btnThree = findViewById<Button>(R.id.btnTwo)
    val btnFour = findViewById<Button>(R.id.btnTwo)
    val btnFive = findViewById<Button>(R.id.btnTwo)
    val btnSix = findViewById<Button>(R.id.btnTwo)
    val btnSeven = findViewById<Button>(R.id.btnTwo)
    val btnEight = findViewById<Button>(R.id.btnTwo)
    val btnNine = findViewById<Button>(R.id.btnTwo)
    val btnZero = findViewById<Button>(R.id.btnTwo)

    // Initialize currentInput as a StringBuilder
    var currentInput = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}