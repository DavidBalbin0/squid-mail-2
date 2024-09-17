package com.david.squid_mail.screen

import android.icu.text.CaseMap.Fold
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.david.squid_mail.R
import com.david.squid_mail.components.DefaultTopBar
import com.david.squid_mail.components.DrawerMenu
import com.david.squid_mail.components.EmailComponent
import com.david.squid_mail.components.SelectionModeTopBar
import com.david.squid_mail.model.EmailPreview
import com.david.squid_mail.model.Folder
import kotlinx.coroutines.launch


@Composable
fun InboxScreen(

    viewModel: InboxViewModel,
    navController: NavController
) {

    

    val emailPreviews = viewModel.emails.map { EmailPreview(it) }
    val isSelectionMode by viewModel.isSelectionMode
    val selectedEmails = viewModel.selectedEmails

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navigationItems = remember {
        listOf(
            Folder(1, "Folder", 1),
            Folder(1, "Folder", 1)
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerMenu(
                    navigationItems = navigationItems,
                    onCloseMenu = { scope.launch { drawerState.close() } },
                    onNavigateTo = { route ->
                        navController.navigate(route)
                    }
                )
            }
        }) {
        Scaffold(
            topBar = {
                if (isSelectionMode) {
                    SelectionModeTopBar(
                        selectedCount = selectedEmails.size,
                        onCancelSelection = { viewModel.cancelSelection() }
                    )
                } else {
                    DefaultTopBar(unreadEmailsCount = 5, onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    })
                }
            },
            floatingActionButton = {
                IconButton(
                    onClick = {
                        navController.navigate("email-composition")
                    }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Compose email")
                }
                }

        ) { paddingValues ->
            LazyColumn(contentPadding = paddingValues) {
                items(emailPreviews) { email ->
                    EmailComponent(
                        emailPreview = email,
                        onLongClick = {
                            if (!isSelectionMode) {
                                viewModel.enterSelectionMode()
                            }
                            viewModel.toggleSelection(email)
                        },
                        onClick = {
                            if (isSelectionMode) {
                                viewModel.toggleSelection(email)
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


@Preview(showSystemUi = true)
@Composable
fun InboxScreenPreview() {
    val viewModel = InboxViewModel()
    val navController = rememberNavController()
    InboxScreen(
        viewModel, navController
    )
}