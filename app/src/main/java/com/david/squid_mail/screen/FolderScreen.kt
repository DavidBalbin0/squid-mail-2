package com.david.squid_mail.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.david.squid_mail.R
import com.david.squid_mail.model.Folder

@Composable
fun FolderScreen(
    folderViewModel: FolderViewModel,
    navController: NavController
) {
    var folderField by folderViewModel.folderField

    LaunchedEffect(Unit){
        folderViewModel.fetchFolders()
    }

    Scaffold {
     Column(
            modifier = Modifier.padding(it)
     ) {
         OutlinedTextField(value = folderField, onValueChange = {
             folderViewModel.onFolderFieldChange(it)
         } )

            // Display the folder list
         LazyColumn(content = {
             items(folderViewModel.folderList.value){ folder ->
                 FolderComponent(folder = folder, onDelete = {
                     // Delete the folder
                 })
             }
         })
     }
    }
}

@Composable
fun FolderComponent(folder: Folder, onDelete: () -> Unit) {
    // Display the folder
    Row {
        Text(text = folder.name)

        Icon(
            painter = painterResource(id = R.drawable.baseline_delete_24),
            contentDescription = "delete folder",
            modifier = Modifier.clickable { onDelete() }
        )
    }

}