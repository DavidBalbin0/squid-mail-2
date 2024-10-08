package com.david.squid_mail.screen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.david.squid_mail.components.DefaultTopBar
import com.david.squid_mail.components.DrawerMenu
import com.david.squid_mail.components.EmailComponent
import com.david.squid_mail.components.SelectionModeTopBar
import com.david.squid_mail.model.Folder
import com.david.squid_mail.model.FolderType
import kotlinx.coroutines.launch


@Composable
fun EmailScreen(

    viewModel: EmailViewModel,
    navController: NavController,

) {

    val emailPreviews by viewModel.emailPreviews.observeAsState(initial = emptyList())
    val isSelectionMode by viewModel.isSelectionMode
    val selectedEmails = viewModel.selectedEmails
    val folderType = viewModel.folderType
    val folderId = viewModel.folderId

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchEmails()
        viewModel.fetchFolders()
    }

    val folderList by viewModel.folderList.collectAsState()
    Log.i("EmailScreen", "folderList: $folderList")
    Log.i("EmailScreen", "folderID: $folderId")

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerMenu(
                    folderList,
                    onCloseMenu = {
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    navController
                )
            }
        }) {
        Scaffold(
            topBar = {
                if (isSelectionMode) {
                    SelectionModeTopBar(
                        selectedCount = selectedEmails.size,
                        onCancelSelection = { viewModel.cancelSelection() },
                        onArchive = { viewModel.archiveSelectedEmails() },
                        onMoveToFolder = { viewModel.moveToFolderSelectedEmails(it) },
                        onMarkAsRead = { viewModel.markAsReadSelectedEmails() },
                        folderList = folderList
                    )
                } else {
                    DefaultTopBar(
                        unreadEmailsCount = 5, onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        folderName =  folderList.find { it.id == folderId}?.name ?: folderType.displayName
                    )
                }
            },
            floatingActionButton = {
                IconButton(
                    onClick = {
                        navController.navigate("email-composition")
                    }
                ) {
                    Icon(Icons.Filled.Create, contentDescription = "Compose email",
                        modifier = Modifier
                            .size(100.dp)

                            )


                }
            }

        ) { paddingValues ->
            LazyColumn(contentPadding = paddingValues) {
                items(emailPreviews) { preview ->
                    EmailComponent(
                        emailPreview = preview,
                        onStarClick = { viewModel.toggleFavorite(preview.email) },
                        onLongClick = {
                            if (!isSelectionMode) {
                                viewModel.enterSelectionMode()
                            }
                            viewModel.toggleSelection(preview)
                        },
                        onClick = {
                            if (isSelectionMode) {
                                viewModel.toggleSelection(preview)
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
    val viewModel = EmailViewModel(LocalContext.current)
    val navController = rememberNavController()
    EmailScreen(
        viewModel, navController
    )
}