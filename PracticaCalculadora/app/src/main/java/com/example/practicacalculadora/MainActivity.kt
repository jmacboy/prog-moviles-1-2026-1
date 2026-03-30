package com.example.practicacalculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.practicacalculadora.ui.theme.PracticaCalculadoraTheme
import com.example.practicacalculadora.viewmodels.CalculatorViewModel
import com.example.practicacalculadora.views.CalculatorScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaCalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen(
                        CalculatorViewModel(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
