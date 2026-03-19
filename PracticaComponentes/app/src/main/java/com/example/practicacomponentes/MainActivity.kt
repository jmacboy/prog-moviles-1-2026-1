package com.example.practicacomponentes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicacomponentes.ui.theme.PracticaComponentesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaComponentesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            value = nombre,
            label = {
                Text("Nombre")
            },
            onValueChange = {
                nombre = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        TextField(
            value = apellido,
            label = {
                Text("Apellido")
            }, onValueChange = {
                apellido = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)

        )
        Button(
            onClick = {
                Toast.makeText(context, "$nombre $apellido", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth()

        ) {
            Text("Enviar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticaComponentesTheme {
        HomeScreen()
    }
}