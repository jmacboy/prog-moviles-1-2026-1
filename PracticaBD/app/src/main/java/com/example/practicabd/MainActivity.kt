package com.example.practicabd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practicabd.ui.screens.FormScreen
import com.example.practicabd.ui.screens.HomeScreen
import com.example.practicabd.ui.screens.NavScreens
import com.example.practicabd.ui.theme.PracticaBDTheme
import com.example.practicabd.ui.viewmodels.HomeScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           NavigationApp()
        }
    }
    @Composable
    fun NavigationApp(navController: NavHostController = rememberNavController()){
        PracticaBDTheme {
            NavHost(
                navController = navController,
                startDestination = NavScreens.HOME.name
            ) {
                composable(NavScreens.HOME.name) {
                    HomeScreen(modifier = Modifier, navController)
                }
                composable(NavScreens.FORM.name) {
                    FormScreen(modifier = Modifier, navController)
                }
            }
        }
    }
}
