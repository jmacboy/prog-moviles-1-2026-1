package com.example.practicainternet

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
import com.example.practicainternet.ui.NavScreens
import com.example.practicainternet.ui.screens.PostDetailScreen
import com.example.practicainternet.ui.screens.PostListScreen
import com.example.practicainternet.ui.theme.PracticaInternetTheme
import com.example.practicainternet.ui.viewmodels.PostListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaInternetTheme {

                NavigationApp()
            }
        }
    }

    @Composable
    fun NavigationApp(
        navController: NavHostController = rememberNavController(),
        vm: PostListViewModel = PostListViewModel()
    ) {
        NavHost(
            navController = navController,
            startDestination = NavScreens.HOME.name
        ) {
            composable(NavScreens.HOME.name) {
                PostListScreen(modifier = Modifier, vm, navController)
            }
            composable(NavScreens.DETAIL.name ) {
                PostDetailScreen(vm)
            }
        }
    }
}
