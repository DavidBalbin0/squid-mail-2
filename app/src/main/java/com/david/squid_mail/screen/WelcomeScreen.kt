package com.david.squid_mail.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.coroutineScope

@Composable

fun WelcomeScreen(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Bem-vindo",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 56.dp)
            )
        Text(
            text = "Faça login ou cadastre-se para continuar",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 250.dp)
            )

        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier.fillMaxWidth().padding(top = 150.dp)
        ) {
            Text("Entrar")
        }
        Button(
            onClick = { navController.navigate("register")  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar-se")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    var navController = rememberNavController()
    WelcomeScreen(navController)
}

