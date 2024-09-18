package com.david.squid_mail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.david.squid_mail.R
import com.david.squid_mail.ui.theme.LogoColor
import java.util.jar.Attributes.Name

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel,
    navController: NavController
) {
    val email by viewModel::email
    val name by viewModel::name
    val password by viewModel::password
    val confirmPassword by viewModel::confirmPassword
    val passwordVisible by viewModel::passwordVisible
    val emailError by viewModel::emailError
    val passwordError by viewModel::passwordError
    val confirmPasswordError by viewModel::confirmPasswordError
    val isFormValid by viewModel::isFormValid

    var generalError by remember { mutableStateOf("") }

    Spacer(modifier = Modifier.height(500.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF278AB0))
            .padding(bottom = 30.dp),
        horizontalArrangement = Arrangement.Center, // Centraliza o conteúdo horizontalmente
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Icon(
            painter = painterResource(id = R.drawable.octa_logo),
            contentDescription = "Icon Centered",
            tint = LogoColor,
            modifier = Modifier
                .size(150.dp)
                .padding(top = 50.dp) // Adiciona 16.dp de espaçamento na parte superior
        )

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Text(
            text = "Crie sua conta",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Email Field
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.onNameChange(it) }, // Ajustado para capturar mudanças no nome
            label = { Text("Insira seu nome") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, // Altere para KeyboardType.Text
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onEmailChange(it)},
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            isError = emailError.isNotEmpty(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError.isNotEmpty(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) R.drawable.gps_not_fixed else R.drawable.gps_off
                IconButton(onClick = { viewModel.onPasswordVisibilityChange() }) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // pass confirmation field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordChange(it) },
            label = { Text("Confirme a Senha") },
            modifier = Modifier.fillMaxWidth(),
            isError = confirmPasswordError.isNotEmpty(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { viewModel.onPasswordVisibilityChange() }) {
                    val image = if (passwordVisible) R.drawable.gps_not_fixed else R.drawable.gps_off
                    IconButton(onClick = { viewModel.onPasswordVisibilityChange() }) {
                        Icon(
                            painter = painterResource(id = image),
                            contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        if (confirmPasswordError.isNotEmpty()) {
            Text(
                text = confirmPasswordError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Register Button
        Button(
            onClick = {
                viewModel.submitForm(
                    onSuccess = {
                        // Navegar para a próxima tela após o cadastro bem-sucedido
                        navController.navigate("login") // Ajuste a rota conforme necessário
                    },
                    onError = { errorMsg ->
                        // Atualizar o estado de erro geral para exibir uma mensagem
                        generalError = errorMsg
                    }
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFormValid
        ) {
            Text("Cadastrar")
        }

        // Placeholder for Error Message
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Já está registrado? Clique aqui para logar.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { navController.navigate("login") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (generalError.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = generalError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}

// Function to validate email format
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

// Function to validate password requirements
fun isValidPassword(password: String): Boolean {
    // Regular expression to check the password requirements

    // - At least 8 characters long
    // - Contains at least one uppercase letter (A-Z)
    // - Contains at least one lowercase letter (a-z)
    // - Contains at least one digit (0-9)
    // - Contains at least one special character (@, $, !, %, *, ?, &)
    val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
    val passwordMatcher = Regex(passwordPattern)

    // Returns true if the password matches the pattern, false otherwise
    return passwordMatcher.matches(password)
}


@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    val navController = rememberNavController()
    // Para a pré-visualização, podemos criar uma instância de RegistrationViewModel com valores predefinidos
    val fakeViewModel = RegistrationViewModel().apply {
        // Definir valores de teste
        email = "test@example.com"
        password = "Password1!"
        confirmPassword = "Password1!"
        isFormValid = true
    }
    RegistrationScreen(viewModel = fakeViewModel, navController = navController)
}