package com.example.kotlin_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View;
import android.widget.Button;
import android.widget.Toast
import com.example.kotlin_calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var newOperator = true
    var oldInput = ""
    var newInput = ""
    var operation = ""
    var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun btnClick(view: View) {
        if (newOperator)
            binding.txtResult.text = ""
        binding.btnEquals.isEnabled = true
        newOperator = false
        var btnClick: String = binding.txtResult.text.toString()
        var btnSelect: Button = view as Button
        when(btnSelect){
            binding.btn1 -> btnClick += "1"
            binding.btn2 -> btnClick += "2"
            binding.btn3 -> btnClick += "3"
            binding.btn4 -> btnClick += "4"
            binding.btn5 -> btnClick += "5"
            binding.btn6 -> btnClick += "6"
            binding.btn7 -> btnClick += "7"
            binding.btn8 -> btnClick += "8"
            binding.btn9 -> btnClick += "9"
            binding.btn0 -> btnClick += "0"
            binding.btnDot -> btnClick += "."
        }
        binding.txtResult.text = btnClick
        if (binding.btnDot.isPressed){
            binding.btnDot.isEnabled = false
        }
        newInput = binding.txtResult.text.toString()
    }

    fun btnClear(view: View) {
        operation = ""
        binding.txtResult.text = ""
        binding.btnDot.isEnabled = true
        binding.btnEquals.isEnabled = false
    }

    fun btnBackSpace(view: View) {
        val length = binding.txtResult.length()
        if (length > 0)
            binding.txtResult.text = binding.txtResult.text.subSequence(0, length - 1)
        if (!binding.txtResult.text.contains("."))
            binding.btnDot.isEnabled = true
    }

    fun btnOperations(view: View) {
        newOperator = true
        oldInput = binding.txtResult.text.toString()
        var btnSelect: Button = view as Button
        when(btnSelect){
            binding.btnAdd -> {operation = "+"}
            binding.btnMinus -> {operation = "-"}
            binding.btnMultiply -> {operation = "x"}
            binding.btnDivide -> {operation = "÷"}
        }
        binding.btnEquals.isEnabled = false
    }

    fun btnEquals(view: View) {
        if (newInput.isNotEmpty()){
            if (operation.isNotEmpty()){
                when(operation){
                    "+" -> {result = oldInput.toDouble() + newInput.toDouble()
                        binding.txtResult.text = result.toString()}
                    "-" -> {result = oldInput.toDouble() - newInput.toDouble()
                        binding.txtResult.text = result.toString()
                    }
                    "x" -> {result = oldInput.toDouble() * newInput.toDouble()
                        binding.txtResult.text = result.toString()
                    }
                    "÷" -> {result = oldInput.toDouble() / newInput.toDouble()
                        binding.txtResult.text = result.toString()
                    }
                }
            } else{
                Toast.makeText(applicationContext, "Can't compute missing input.", Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(applicationContext, "Can't compute missing input.", Toast.LENGTH_SHORT).show()
        }
        newOperator = true
        oldInput = binding.txtResult.text.toString()
    }
}