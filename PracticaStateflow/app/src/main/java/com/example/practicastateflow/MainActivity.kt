package com.example.practicastateflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.practicastateflow.ui.theme.PracticaStateflowTheme
import com.example.practicastateflow.viewmodels.LoginViewModel
import com.example.practicastateflow.viewmodels.RegisterViewModel
import com.example.practicastateflow.views.LoginScreen
import com.example.practicastateflow.views.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var showLogin by remember { mutableStateOf(false) }
            PracticaStateflowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (showLogin) {
                        LoginScreen(
                            vm = LoginViewModel(),
                            modifier = Modifier.padding(innerPadding),
                            onNavigateToRegister = {
                                showLogin = false
                            }
                        )
                    } else {
                        RegisterScreen(
                            vm = RegisterViewModel(),
                            modifier = Modifier.padding(innerPadding),
                            onNavigateToLogin = {
                                showLogin = true
                            }
                        )
                    }
                }
            }
        }
    }
}
