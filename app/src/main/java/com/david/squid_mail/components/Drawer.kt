package com.david.squid_mail.components

import android.R
import android.R.drawable.ic_menu_edit
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.squid_mail.screen.MenuItem
import kotlinx.coroutines.Job

@Composable
fun DrawerMenu(onCloseMenu: () -> Job) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
//            .wrapContentHeight()
            .width(250.dp)
            .background(Color(0xFF1C4670)) // Azul similar ao da imagem
            .padding(16.dp)
//        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu_close_clear_cancel),
            contentDescription = "Close Menu",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
                .clickable { onCloseMenu() }
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            item {
                MenuItem(icon = R.drawable.ic_menu_send, label = "Caixa de Entrada")
                MenuItem(icon = ic_menu_edit, label = "Rascunhos")
                MenuItem(icon = R.drawable.ic_menu_save, label = "Arquivados")
                MenuItem(icon = R.drawable.ic_menu_send, label = "Enviados")
                MenuItem(icon = R.drawable.btn_star_big_off, label = "Favoritos")
                MenuItem(icon = R.drawable.ic_menu_delete, label = "Deletados")
                MenuItem(icon = R.drawable.ic_menu_agenda, label = "Spam")
                MenuItem(icon = R.drawable.ic_menu_preferences, label = "Configurações")
                MenuItem(icon = R.drawable.ic_menu_today, label = "Calendário")
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Pastas",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                MenuItem(icon = R.drawable.ic_menu_edit, label = "Trabalho")
                Spacer(modifier = Modifier.width(16.dp))
                MenuItem(icon = ic_menu_edit, label = "Mvp Fiap")
                Spacer(modifier = Modifier.width(32.dp))
                MenuItem(icon = ic_menu_edit, label = "Traffic Guard")
                Spacer(modifier = Modifier.width(32.dp))
                MenuItem(icon = ic_menu_edit, label = "Eazy Agro")

                Spacer(modifier = Modifier.width(16.dp))
                MenuItem(icon = ic_menu_edit, label = "Igreja")
                Spacer(modifier = Modifier.width(32.dp))
                MenuItem(icon = ic_menu_edit, label = "Faculdade")
                Spacer(modifier = Modifier.width(32.dp))
                MenuItem(icon = ic_menu_edit, label = "Leads")
                MenuItem(icon = ic_menu_edit, label = "Família")
                MenuItem(icon = ic_menu_edit, label = "Amigos")
                Spacer(modifier = Modifier.weight(1f))


            }
        }

    }

}

