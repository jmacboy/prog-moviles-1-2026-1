package com.example.practicanavegacion.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.practicanavegacion.ui.NavScreens
import com.example.practicanavegacion.ui.theme.PracticaNavegacionTheme


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navConroller: NavController = rememberNavController()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            Text("Primera pantalla")
            Button(
                onClick = {
                    navConroller.navigate(NavScreens.SECOND.name)
                }
            ) {
                Text("Ir a la segunda pantalla")
            }
        }
    }
}
