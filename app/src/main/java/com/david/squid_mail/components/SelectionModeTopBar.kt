package com.david.squid_mail.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionModeTopBar(selectedCount: Int, onCancelSelection: () -> Unit, onArchive: () -> Unit, onMarkAsRead: () -> Unit, onMoveToFolder: () -> Unit ) {
    TopAppBar(
        title = { Text("$selectedCount selected") },
        navigationIcon = {
            IconButton(onClick = onCancelSelection) {
                Icon(Icons.Default.Close, contentDescription = "Cancel selection")
            }
        },
        actions = {
            // Botão de mover para pasta
            IconButton(onClick = { /* Move action */ }) {
                Icon(Icons.Default.AddCircle, contentDescription = "Move to folder")
            }
            // Botão de arquivar
            IconButton(onClick = { onArchive() }) {
                Icon(Icons.Default.Email, contentDescription = "Archive")
            }
            // Botão de marcar como lido/não lido
            IconButton(onClick = { onMarkAsRead}) {
                Icon(Icons.Default.MailOutline, contentDescription = "Mark as read")
            }
        }
    )
}
