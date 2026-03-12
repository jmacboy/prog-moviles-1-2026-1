package com.example.pruebaholamundo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.pruebaholamundo.ui.theme.PruebaHolaMundoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaHolaMundoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(
                context,
                "Prueba toast",
                Toast.LENGTH_SHORT
            )
                .show()
        },
        modifier = modifier
    ) {
        Text("Click aquí")
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    PruebaHolaMundoTheme {
        Home()
    }
}