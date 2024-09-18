package com.david.squid_mail.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.david.squid_mail.model.Email
import com.david.squid_mail.model.EmailPreview
import com.david.squid_mail.model.Folder

class FolderViewModel: ViewModel() {

    val folderList = mutableStateOf(listOf<Folder>())
    var folderField = mutableStateOf("")
        private set

    fun onFolderFieldChange(folderField: String) {
        this.folderField.value = folderField
    }

    fun fetchFolders(){
        // Fetch folders from the database
    }
}

