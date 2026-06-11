package com.example.practicacanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicacanvas.ui.theme.PracticaCanvasTheme
import com.example.practicacanvas.ui.screens.CanvasAnimationPage
import com.example.practicacanvas.ui.screens.CanvasPage
import com.example.practicacanvas.ui.screens.NavScreens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaCanvasTheme {
                NavigationApp()

            }
        }
    }

    @Composable
    fun NavigationApp(navController: NavHostController = rememberNavController()) {
        NavHost(
            navController = navController,
            startDestination = NavScreens.ANIMATION.name
        ) {
            composable(NavScreens.CANVAS.name) {
                CanvasPage()
            }
            composable(NavScreens.ANIMATION.name) {
                CanvasAnimationPage()
            }
        }
    }
}
