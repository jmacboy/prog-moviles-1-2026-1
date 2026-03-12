package com.example.practicaactivities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicaactivities.ui.theme.PracticaActivitiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaActivitiesTheme {
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) { //contenedor vertical
            Text("Bienvenido a la pantalla principal")
            HomeButton(
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .background(Color.Black)
                .padding(5.dp, 10.dp)
        ) { //Contenedor horizontal
            Button(
                onClick = {
                    Toast.makeText(context, "Botón de la izquierda", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f)
            ) { Text("Boton 1") }
            Button(
                onClick = {
                    Toast.makeText(context, "Botón de la derecha", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f)
            ) { Text("Boton 2") }
        }
    }
}


@Composable
fun HomeButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(context, SegundoActivity::class.java)
            context.startActivity(intent)
        },
        modifier = modifier
    ) {
        Text("Ir al segundo activity")
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PracticaActivitiesTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeButtonPreview() {
    PracticaActivitiesTheme {
        HomeButton()
    }
}