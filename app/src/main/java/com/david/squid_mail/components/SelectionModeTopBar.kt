package com.david.squid_mail.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.david.squid_mail.model.Folder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionModeTopBar(
    selectedCount: Int,
    onCancelSelection: () -> Unit,
    onArchive: () -> Unit,
    onMarkAsRead: () -> Unit,
    onMoveToFolder: (folderId: Long) -> Unit,
    folderList: List<Folder>
) {
    val showDialog = remember { mutableStateOf(false) }


    TopAppBar(
        title = { Text("$selectedCount selected") },
        navigationIcon = {
            IconButton(onClick = onCancelSelection) {
                Icon(Icons.Default.Close, contentDescription = "Cancel selection")
            }
        },
        actions = {
            // Botão de mover para pasta
            IconButton(onClick = { showDialog.value = true }) {
                Icon(Icons.Default.AddCircle, contentDescription = "Move to folder")
            }
            // Botão de arquivar
            IconButton(onClick = { onArchive() }) {
                Icon(Icons.Default.Email, contentDescription = "Archive")
            }
            // Botão de marcar como lido/não lido
            IconButton(onClick = { onMarkAsRead }) {
                Icon(Icons.Default.MailOutline, contentDescription = "Mark as read")
            }
        }
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Select Folder") },
            text = {
                Text("Select the folder to move the emails to")
                LazyColumn {
                    items(folderList) { folder ->
                        Button(onClick = {
                            showDialog.value = false
                            onMoveToFolder(folder.id)
                        }) {
                            Text(text = folder.name)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {

                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
