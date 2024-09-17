package com.david.squid_mail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.squid_mail.R
import com.david.squid_mail.model.Folder

@Composable
fun DrawerMenu(
    navigationItems: List<Folder>,
    onCloseMenu: () -> Unit,
    onNavigateTo: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .width(250.dp)
            .background(Color(0xFF1C4670)) // Azul similar ao da imagem
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_close_24),
            contentDescription = "Close Menu",
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable { onCloseMenu() }
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            item {
                MenuItem(icon = R.drawable.baseline_inbox_24, label = "Caixa de Entrada", {})
                MenuItem(icon = R.drawable.baseline_draw_24, label = "Rascunhos", {})
                MenuItem(icon = R.drawable.baseline_folder_24, label = "Arquivados" , {})
                MenuItem(icon = R.drawable.baseline_send_24, label = "Enviados", {})
                MenuItem(icon = R.drawable.baseline_star_border_24, label = "Favoritos", {})
                MenuItem(icon = R.drawable.baseline_delete_24, label = "Deletados", {})
                MenuItem(icon = R.drawable.baseline_dangerous_24, label = "Spam", {})
                MenuItem(icon = R.drawable.baseline_settings_24, label = "Configurações", {})
                MenuItem(icon = R.drawable.baseline_calendar_today_24, label = "Calendário", {})
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Pastas",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(navigationItems) { item ->
                MenuItem(
                    icon = R.drawable.baseline_folder_24,
                    label = item.name,
                    onClick = {
                        onNavigateTo(item.name)
                        onCloseMenu()
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
        Spacer(modifier = Modifier.weight(1f))

    }

}

