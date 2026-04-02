package com.example.practicastateflow.views

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicastateflow.ui.theme.PracticaStateflowTheme
import com.example.practicastateflow.viewmodels.LoginViewModel


@Composable
fun LoginScreen(vm: LoginViewModel, modifier: Modifier = Modifier) {
    val state = vm.state.collectAsState()
    val context = LocalContext.current
    if (state.value.loginSuccess == true) {
        Toast.makeText(context, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
        vm.resetLoginSuccess()
    }
    if (state.value.errorMessage != null) {
        Toast.makeText(context, state.value.errorMessage, Toast.LENGTH_SHORT).show()
        vm.errorShown()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Pantalla de inicio de sesión",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = state.value.username,
            onValueChange = {
                vm.updateUsername(it)
            },
            label = { Text("Usuario") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        TextField(
            value = state.value.password,
            onValueChange = {
                vm.updatePassword(it)
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = { vm.login() },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PracticaStateflowTheme {
        LoginScreen(LoginViewModel())
    }
}