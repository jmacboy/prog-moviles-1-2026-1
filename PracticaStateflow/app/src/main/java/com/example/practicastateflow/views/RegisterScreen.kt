package com.example.practicastateflow.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicastateflow.ui.theme.PracticaStateflowTheme
import com.example.practicastateflow.viewmodels.RegisterViewModel


@Composable
fun RegisterScreen(
    vm: RegisterViewModel,
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit
) {
    val state = vm.state.collectAsState()
//    val context = LocalContext.current
//    if (state.value.registerSuccess == true) {
//        Toast.makeText(context, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show()
//        vm.resetLoginSuccess()
//    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Pantalla de Registro",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = state.value.fullName,
            onValueChange = {
                vm.updateFullName(it)
            },
            label = { Text("Nombre Completo") },
            isError = state.value.showFullNameError,
            supportingText = {
                if (state.value.showFullNameError) {
                    Text("El nombre completo es obligatorio")
                }
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = state.value.username,
            onValueChange = {
                vm.updateUsername(it)
            },
            isError = state.value.showUsernameError,
            supportingText = {
                if (state.value.showUsernameError) {
                    Text("El nombre de usuario es obligatorio")
                }
            },
            label = { Text("Usuario") },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = state.value.password,
            onValueChange = {
                vm.updatePassword(it)
            },
            isError = state.value.showPasswordError,
            supportingText = {
                if (state.value.showPasswordError) {
                    Text("La contraseña es obligatoria")
                }
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {
                vm.register()
                onNavigateToLogin()
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Registrar usuario")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PracticaStateflowTheme {
        RegisterScreen(RegisterViewModel(), onNavigateToLogin = {})
    }
}