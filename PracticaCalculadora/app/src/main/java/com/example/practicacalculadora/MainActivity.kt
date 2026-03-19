package com.example.practicacalculadora

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicacalculadora.StyledButton
import com.example.practicacalculadora.ui.theme.PracticaCalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaCalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    //state hoisting - > el estado se maneja en un nivel superior y se pasa a los componentes hijos
    var result by remember { mutableStateOf("") }
    var prevNumber by remember { mutableIntStateOf(0) }
    var currentOperation by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            result.ifEmpty { "0" },
            fontSize = 40.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
        NumbersPanel(onNumberClick = {
            // el valor recibido dentro de una función lambda siempre se llama it
            result += it
        })
        OperationsPanel(
            startOperation = { operationType -> // se renombra la variable it a operationType
                prevNumber = result.toIntOrNull() ?: 0
                result = ""
                currentOperation = operationType
            },
            doOperation = {
                val currentNumber = result.toIntOrNull() ?: 0
                val operationResult = when (currentOperation) {
                    "+" -> prevNumber + currentNumber
                    "-" -> prevNumber - currentNumber
                    "*" -> prevNumber * currentNumber
                    "/" -> {
                        if (currentNumber == 0) {
                            Toast.makeText(context, "Error, división por cero", Toast.LENGTH_SHORT)
                                .show()
                            return@OperationsPanel
                        }
                        prevNumber / currentNumber
                    }

                    else -> currentNumber
                }
                result = operationResult.toString()
            }

        )
        ClearOperations(
            onClearEverythingClick = {
                result = ""
                prevNumber = 0
                currentOperation = ""
            },
            onClearOneClick = {
                if (result.isNotEmpty()) {
                    result = result.dropLast(1)
                }
            },
            onClearClick = {
                result = ""
            }
        )
    }
}

@Composable
fun RowScope.StyledButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        modifier = Modifier.weight(1f)
    ) {
        Text(text, fontSize = 30.sp)
    }
}

@Composable
fun ClearOperations(
    onClearEverythingClick: () -> Unit,
    onClearOneClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        StyledButton(
            onClick = { onClearOneClick() },
            text = "<-"
        )
        StyledButton(
            onClick = { onClearClick() },
            "C",
        )
        StyledButton(
            onClick = { onClearEverythingClick() },
            "CE"
        )
    }
}

@Composable
fun OperationsPanel(
    startOperation: (String) -> Unit,
    doOperation: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        StyledButton(
            onClick = { startOperation("+") },
            "+"
        )
        StyledButton(
            onClick = { startOperation("-") },
            "-"
        )
        StyledButton(
            onClick = { startOperation("*") },
            "x"
        )
        StyledButton(
            onClick = { startOperation("/") },
            "/"
        )
    }
    Row(
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        StyledButton(
            onClick = { doOperation() },
            "="
        )
    }
}

@Composable
fun NumbersPanel(onNumberClick: (String) -> Unit) {
    Row(
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        StyledButton(
            onClick = { onNumberClick("1") }, "1"
        )

        StyledButton(
            onClick = { onNumberClick("2") }, "2"
        )

        StyledButton(
            onClick = { onNumberClick("3") }, "3"
        )

    }
    Row(
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        StyledButton(
            onClick = { onNumberClick("4") }, "4"
        )

        StyledButton(
            onClick = { onNumberClick("5") }, "5"
        )

        StyledButton(
            onClick = { onNumberClick("6") }, "6"
        )

    }
    Row(
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        StyledButton(
            onClick = { onNumberClick("7") }, "7"
        )

        StyledButton(
            onClick = { onNumberClick("8") },
            "8"
        )

        StyledButton(
            onClick = { onNumberClick("9") }, "9"
        )

    }
    Row(
        modifier = Modifier.padding(0.dp, 8.dp)
    ) {
        StyledButton(
            onClick = { onNumberClick("0") }, "0"
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaCalculadoraTheme {
        CalculatorScreen()
    }
}