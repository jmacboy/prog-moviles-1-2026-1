package com.example.practicaapiviajes

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
import com.example.practicaapiviajes.ui.NavScreens
import com.example.practicaapiviajes.ui.screens.TripFormScreen
import com.example.practicaapiviajes.ui.screens.TripListScreen
import com.example.practicaapiviajes.ui.theme.PracticaApiViajesTheme
import com.example.practicaapiviajes.ui.viewmodels.TripFormViewModel
import com.example.practicaapiviajes.ui.viewmodels.TripListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaApiViajesTheme {
                NavigationApp()
            }
        }
    }


    @Composable
    fun NavigationApp(
        navController: NavHostController = rememberNavController(),
        vm: TripListViewModel = TripListViewModel(),
        formVm: TripFormViewModel = TripFormViewModel()
    ) {

        NavHost(
            navController = navController,
            startDestination = NavScreens.HOME.name
        ) {
            composable(NavScreens.HOME.name) {
                TripListScreen(modifier = Modifier, vm, navController)
            }
            composable(NavScreens.TRIP_FORM.name) {
                TripFormScreen(modifier = Modifier, vm = formVm, navController)
            }
        }
    }
}