package com.david.squid_mail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.david.squid_mail.R
import com.david.squid_mail.ui.theme.ButtonWhite
import com.david.squid_mail.ui.theme.LogoColor
import com.david.squid_mail.ui.theme.TextBlue

@Composable
fun WelcomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF278AB0)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(

                painter = painterResource(id = R.drawable.octa_logo), // Substitua pelo nome do seu ícone
                contentDescription = "Descrição do ícone", // Descrição do ícone para acessibilidade
                tint = LogoColor,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Bem-vindo ao SquidMail",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Conte com a gente para facilitar sua comunicação, sempre com segurança e praticidade",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Faça login ou cadastre-se para continuar",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Button(
                colors = ButtonDefaults.buttonColors(ButtonWhite),
                onClick = { /* Simular navegação / },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text("Entrar")
            }
            Button(
                onClick = { / Simular navegação */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Já tenho conta",
                    color = TextBlue,
                    fontWeight = FontWeight.Bold

                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                colors = ButtonDefaults.buttonColors(ButtonWhite),
                onClick = { /* Simular navegação / },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Text("Entrar")
            }
            Button(
                onClick = { / Simular navegação */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Quero me cadastrar",
                    color = TextBlue,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}