package com.david.squid_mail.components

import android.app.Dialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.david.squid_mail.R
import com.david.squid_mail.model.Folder

@Composable
fun DrawerMenu(
    folders: List<Folder>,
    onCloseMenu: () -> Unit,
    navController: NavController

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
                MenuItem(icon = R.drawable.baseline_inbox_24, label = "Caixa de Entrada") {
                    navController.navigate(
                        "inbox"
                    )
                }
                MenuItem(icon = R.drawable.baseline_draw_24, label = "Rascunhos"){
                    navController.navigate(
                        "drafts"
                    )
                }
                MenuItem(icon = R.drawable.baseline_folder_24, label = "Arquivados" ){
                    navController.navigate(
                        "archived"
                    )
                }
                MenuItem(icon = R.drawable.baseline_send_24, label = "Enviados", {navController.navigate("sent")})
                MenuItem(icon = R.drawable.baseline_star_border_24, label = "Favoritos", {navController.navigate("favorites")})
                MenuItem(icon = R.drawable.baseline_delete_24, label = "Deletados", {navController.navigate("deleted")})
                MenuItem(icon = R.drawable.baseline_dangerous_24, label = "Spam", {navController.navigate("spam")})
                MenuItem(icon = R.drawable.baseline_settings_24, label = "Configurações", {navController.navigate("settings")})
                MenuItem(icon = R.drawable.baseline_calendar_today_24, label = "Calendário", {navController.navigate("calendar")})
                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Text(
                        text = "Pastas",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_create_new_folder_24),
                        contentDescription = "add a folder",
                        modifier = Modifier.clickable {
                            // Open dialog to add a folder
                            navController.navigate("folder")
                        }
                    )
                }

            }
            items(folders) { item ->
                MenuItem(
                    icon = R.drawable.baseline_folder_24,
                    label = item.name,
                    onClick = {
                        Log.i("DrawerMenu", "Folder ${item.id} clicked")
                        navController.navigate("dynamic/${item.id}")
                        onCloseMenu()
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
        Spacer(modifier = Modifier.weight(1f))

    }

}



