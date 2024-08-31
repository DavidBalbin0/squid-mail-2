package com.david.squid_mail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SideMenu() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(Color(0xFF1976D2)) // Azul similar ao da imagem
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
            contentDescription = "Close Menu",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            item {
                MenuItem(icon = android.R.drawable.ic_menu_send, label = "Caixa de Entrada")
                MenuItem(icon = android.R.drawable.ic_menu_edit, label = "Rascunhos")
                MenuItem(icon = android.R.drawable.ic_menu_save, label = "Arquivados")
                MenuItem(icon = android.R.drawable.ic_menu_send, label = "Enviados")
                MenuItem(icon = android.R.drawable.btn_star_big_off, label = "Favoritos")
                MenuItem(icon = android.R.drawable.ic_menu_delete, label = "Deletados")
                MenuItem(icon = android.R.drawable.ic_menu_agenda, label = "Spam")
                MenuItem(icon = android.R.drawable.ic_menu_preferences, label = "Configurações")
                MenuItem(icon = android.R.drawable.ic_menu_today, label = "Calendário")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Pastas", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                MenuItem(icon = android.R.drawable.ic_menu_edit, label = "Trabalho")
                MenuItem(icon = android.R.drawable.ic_menu_edit, label = "Escola")
                MenuItem(icon = android.R.drawable.ic_menu_edit, label = "Família")
                MenuItem(icon = android.R.drawable.ic_menu_edit, label = "Amigos")
                Spacer(modifier = Modifier.weight(1f))
                MenuItem(icon = android.R.drawable.ic_menu_close_clear_cancel, label = "Sair")
            }
        }
    }
}

@Composable
fun MenuItem(icon: Int, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = label, color = Color.White, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSideMenu() {
    SideMenu()
}
