package com.david.squid_mail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar with Close Icon and Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                contentDescription = null,
                tint = Color(0xFF254D70), // Dark blue color
                modifier = Modifier
                    .clickable {navController.popBackStack() }
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Configurações",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF254D70)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Menu Items
        MenuItemSettings(iconResource = android.R.drawable.ic_menu_preferences, title = "Configuração de Tema")
        MenuItemSettings(iconResource = android.R.drawable.ic_menu_edit, title = "Meu Perfil")

        Spacer(modifier = Modifier.height(24.dp))

        // "Demais Configurações" with dropdown indicator
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Demais configurações",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF254D70)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_more),
                contentDescription = null,
                tint = Color(0xFF254D70),
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sub-items under "Demais configurações"
        SubMenuItemSettings(title = "Preferências de notificação")
        SubMenuItemSettings(title = "Alteração de senha")
        SubMenuItemSettings(title = "Configurações de conta")
        SubMenuItemSettings(title = "Sobre o Aplicativo")

        Spacer(modifier = Modifier.weight(1f))

        // Bottom items
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Suporte e Ajuda",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF254D70)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // App Version at the Bottom
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Squidmail v1.0",
                fontSize = 12.sp,
                color = Color(0xFF254D70)
            )
        }
    }
}

@Composable
fun MenuItemSettings(iconResource: Int, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color(0xFF254D70)
        )
    }
}

@Composable
fun SubMenuItemSettings(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        color = Color(0xFF254D70),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 8.dp, bottom = 8.dp)
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSettingsScreen() {
//    MaterialTheme {
//        Surface {
//            SettingsScreen(onCloseClick = {})
//        }
//    }
//}
