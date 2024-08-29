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
import kotlinx.coroutines.coroutineScope

@Composable

fun WelcomeScreen(){
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
            modifier = Modifier.padding(bottom = 16.dp)
            )
        Text(
            text = "Faça login ou cadastre-se para continuar",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 50.dp)
            )

        Button(
            onClick = { /* Registration logic */  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen()
}

