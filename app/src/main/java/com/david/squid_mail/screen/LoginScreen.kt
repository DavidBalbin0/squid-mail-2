package com.david.squid_mail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.david.squid_mail.R
import com.david.squid_mail.ui.theme.BgWhite
import com.david.squid_mail.ui.theme.ButtonWhite
import com.david.squid_mail.ui.theme.LogoColor
import com.david.squid_mail.ui.theme.TextBlue
import com.david.squid_mail.ui.theme.TextWhite
//import com.david.squid_mail.ui.theme.textWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController){
    val email by viewModel::email
    val password by viewModel::password
    val emailError by viewModel::emailError
    val passwordError by viewModel::passwordError
    val passwordVisible by viewModel::passwordVisible
    var loginErrorMessage by viewModel::loginErrorMessage

    Surface(
        modifier = Modifier.fillMaxSize(),
//        color = Color(0xFF278AB0)
        color = MaterialTheme.colorScheme.background
    ){
        // commit
        Column (
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(

                painter = painterResource(id = R.drawable.octa_logo), // Substitua pelo nome do seu ícone
                contentDescription = "Descrição do ícone", // Descrição do ícone para acessibilidade
                tint = LogoColor,
                modifier = Modifier.size(150.dp).
                padding(bottom = 36.dp),
            )

            Text (
                text = "Bem-vindo de volta!",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 56.dp),
                color = TextWhite,
                fontWeight = FontWeight.Bold,

            )

            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("E-mail") },
                modifier = Modifier.fillMaxWidth(),
                isError = emailError.isNotEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White, // Fundo branco dentro do contorno
                    focusedBorderColor = Color.Black, // Cor da borda quando focado
                    unfocusedBorderColor = Color.Gray, // Cor da borda quando não focado
                    errorBorderColor = Color.Red // Cor da borda em caso de erro
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

            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
                isError = passwordError.isNotEmpty(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) R.drawable.gps_not_fixed else R.drawable.gps_off
                    IconButton(onClick = { viewModel.togglePasswordVisibility()  }) {
                        Icon(painterResource(id = image), contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
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

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                colors = ButtonDefaults.buttonColors(ButtonWhite),
                onClick = { viewModel.login(
                    onSuccess = { navController.navigate("inbox") },
                    onError = { loginErrorMessage = it },
                    context = navController.context
                ) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login",
                    color = TextBlue,
                    fontWeight = FontWeight.Bold)
            }


            Spacer(modifier = Modifier.height(16.dp))
            Text(

                text = "Não é registrado? Clique aqui.",
                color = TextWhite,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate("register") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            if (loginErrorMessage.isNotEmpty()) {
                Text(
                    text = loginErrorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val viewModel = LoginViewModel()
    val navController = rememberNavController()
    LoginScreen(viewModel = viewModel, navController = navController)
}

