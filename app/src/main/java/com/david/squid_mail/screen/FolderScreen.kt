package com.david.squid_mail.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.david.squid_mail.R
import com.david.squid_mail.model.Folder

@Composable
fun FolderScreen(folderViewModel: FolderViewModel, navController: NavController) {

    var folderName by folderViewModel.folderField
    val folders by folderViewModel.folderList.collectAsState()
    val errorMessage by folderViewModel.errorMessage


    LaunchedEffect(Unit)  {
        folderViewModel.fetchFolders()
    }

    Log.i("FolderScreen", "FolderScreen ${folders.size}")
    // Carrega as pastas na inicialização da tela
    LaunchedEffect(Unit) {
        folderViewModel.fetchFolders()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Campo de texto para adicionar uma nova pasta
        Row(modifier = Modifier.fillMaxWidth()) {

            OutlinedTextField(
                value = folderName,
                onValueChange = { folderName = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                singleLine = true,


            )


            Button(
                onClick = {
                    if (folderName.isNotEmpty()) {
                        folderViewModel.insertFolder()
                    }
                }
            ) {
                Text("Adicionar")
            }



        }
        if (errorMessage != null) {
            Text(errorMessage!!, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp))
// Display the folder list
        LazyColumn(content = {
            items(folders){ folder ->
                    FolderComponent(folder = folder, onDelete = {
                        folderViewModel.deleteFolder(folder)
                    })
            }
        })
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