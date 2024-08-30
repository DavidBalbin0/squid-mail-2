package com.david.squid_mail.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.david.squid_mail.components.DefaultTopBar
import com.david.squid_mail.components.DrawerMenu
import com.david.squid_mail.components.EmailComponent
import com.david.squid_mail.components.SelectionModeTopBar
import com.david.squid_mail.model.Email
import kotlinx.coroutines.launch

@Composable
fun InboxScreen() {
    val emails = remember {
        mutableStateListOf(
            Email(
                "1",
                "Alice",
                "Meeting Tomorrow",
                "Don't forget our meeting...",
                time = "21:45"
            ),
            Email("2", "Bob", "Weekly Report", "Please find attached...", time = "21:45"),
            Email(
                "3",
                "Charlie",
                "Lunch Invitation",
                "How about lunch tomorrow?...",
                time = "21:45"
            ),
            // Mais e-mails...
        )
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var isSelectionMode by remember { mutableStateOf(false) }
    val selectedEmails = remember { mutableStateListOf<Email>() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerMenu()
            }
        }) {
        Scaffold(
            topBar = {
                if (isSelectionMode) {
                    SelectionModeTopBar(selectedCount = selectedEmails.size,
                        onCancelSelection = {
                            isSelectionMode = false
                            selectedEmails.clear()
                            emails.forEach { it.isSelected.value = false }
                        })
                } else {
                    DefaultTopBar(unreadEmailsCount = 5, onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    })
                }
            }
        ) { paddingValues ->
            LazyColumn(contentPadding = paddingValues) {
                items(emails) {
                    EmailComponent(
                        email = it,
                        onLongClick = {
                            if (!isSelectionMode) {
                                isSelectionMode = true
                            }
                            toggleSelection(it, selectedEmails)
                        },
                        onClick = {
                            if (isSelectionMode) {
                                toggleSelection(it, selectedEmails)
                            } else {
                                // Ação normal de abrir o e-mail
                            }
                        }
                    )
                }
            }
        }
    }



}

private fun toggleSelection(email: Email, selectedEmails: MutableList<Email>) {
    email.isSelected.value = !email.isSelected.value

    if (email.isSelected.value) {
        selectedEmails.add(email)
    } else {
        selectedEmails.remove(email)
    }
}

@Preview(showSystemUi = true)
@Composable
fun InboxScreenPreview() {
    InboxScreen()
}