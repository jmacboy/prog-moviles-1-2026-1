package com.example.practicanavegacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicanavegacion.ui.NavScreens
import com.example.practicanavegacion.ui.screens.HomeScreen
import com.example.practicanavegacion.ui.screens.SecondScreen
import com.example.practicanavegacion.ui.theme.PracticaNavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaNavegacionTheme {
                NavigationApp()
            }
        }
    }

    @Composable
    fun NavigationApp(navController: NavHostController = rememberNavController()) {
        NavHost(
            navController = navController,
            startDestination = NavScreens.HOME.name
        ) {
            composable(NavScreens.HOME.name) {
                HomeScreen(modifier = Modifier, navController)
            }
            composable(NavScreens.SECOND.name) {
                SecondScreen()
            }
        }
    }
}