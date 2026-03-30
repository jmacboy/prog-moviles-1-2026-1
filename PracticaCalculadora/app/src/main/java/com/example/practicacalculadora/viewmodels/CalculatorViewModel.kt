package com.example.practicacalculadora.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {


    var result by mutableStateOf("")
        private set
    var memoryResult by mutableStateOf<Int?>(null)
        private set
    var error by mutableStateOf<String?>(null)
        private set
    var prevNumber: Int = 0
    var currentOperation: String = ""

    fun onMemoryAdd() {
        memoryResult = (memoryResult ?: 0) + (result.toIntOrNull() ?: 0)
        result = ""
    }

    fun onMemoryMinus() {
        memoryResult = (memoryResult ?: 0) - (result.toIntOrNull() ?: 0)
        result = ""
    }

    fun onMemoryRecall() {
        result = memoryResult.toString()
    }

    fun onMemoryClear() {
        memoryResult = null
    }

    fun onNumberPressed(num: String) {
        result += num
    }

    fun startOperation(operationType: String) {
        prevNumber = result.toIntOrNull() ?: 0
        result = ""
        currentOperation = operationType
    }

    fun doOperation() {
        val currentNumber = result.toIntOrNull() ?: 0
        val operationResult = when (currentOperation) {
            "+" -> prevNumber + currentNumber
            "-" -> prevNumber - currentNumber
            "*" -> prevNumber * currentNumber
            "/" -> {
                if (currentNumber == 0) {
                    error = "Error: División por cero"
                    return
                }
                prevNumber / currentNumber
            }

            else -> currentNumber
        }
        result = operationResult.toString()
    }

    fun onClearEverything() {
        result = ""
        prevNumber = 0
        currentOperation = ""
    }

    fun onClearOne() {
        if (result.isNotEmpty()) {
            result = result.dropLast(1)
        }
    }

    fun onClear() {
        result = ""
    }

    fun errorShown() {
        error = null
    }

}