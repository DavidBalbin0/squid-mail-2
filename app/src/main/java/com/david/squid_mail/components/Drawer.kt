package com.david.squid_mail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun DrawerMenu() {
    Column {
        Text("Rascunhos", modifier = Modifier.padding(16.dp))
        Text("Enviados", modifier = Modifier.padding(16.dp))
        Text("Deletados", modifier = Modifier.padding(16.dp))
        Text("Configurações", modifier = Modifier.padding(16.dp))
        Text("Calendário", modifier = Modifier.padding(16.dp))
        Text("Pastas", modifier = Modifier.padding(16.dp))
    }
}