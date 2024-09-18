package com.david.squid_mail.components

import android.app.Dialog
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
import com.david.squid_mail.R
import com.david.squid_mail.model.Folder

@Composable
fun DrawerMenu(
    navigationItems: List<Folder>,
    onCloseMenu: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onCreateFolder: (String) -> Unit // Função para criar nova pasta
) {
    var showCreateFolderDialog by remember { mutableStateOf(false) } // Estado do modal
    var newFolderName by remember { mutableStateOf("") } // Nome da nova pasta

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

                Button(onClick = { showCreateFolderDialog = true }) {
                    Text("Criar Nova Pasta")
                }
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

//         Dialog para criar nova pasta
        if (showCreateFolderDialog) {
            AlertDialog(
                onDismissRequest = { showCreateFolderDialog = false },
                title = { Text(text = "Criar Nova Pasta") },
                text = {
                    Column {
                        Text(text = "Digite o nome da nova pasta:")
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = newFolderName,
                            onValueChange = { newFolderName = it },
                            placeholder = { Text(text = "Nome da Pasta") }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newFolderName.isNotEmpty()) {
                                onCreateFolder(newFolderName) // Chama a função para criar a pasta
                                showCreateFolderDialog = false // Fecha o modal
                            }
                        }
                    ) {
                        Text(text = "Criar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showCreateFolderDialog = false }) {
                        Text(text = "Cancelar")
                    }
                }
            )
        }

    }

}



