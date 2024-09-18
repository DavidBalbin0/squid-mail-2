package com.david.squid_mail.screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.david.squid_mail.database.repository.FolderRepository
import com.david.squid_mail.model.Folder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FolderViewModel(context: Context): ViewModel() {

//    val folderList = mutableStateOf(listOf<Folder>())
    var folderField = mutableStateOf("")
        private set

    private var _folderList = MutableStateFlow<List<Folder>>(emptyList())
    val folderList: StateFlow<List<Folder>> = _folderList

    val errorMessage =  mutableStateOf(null as String?)

    val folderRepository = FolderRepository(context)

    init {
        fetchFolders()
    }

    fun onFolderFieldChange(folderField: String) {
        this.folderField.value = folderField
    }

    fun fetchFolders(){
        // Fetch folders from the database
        _folderList.value = folderRepository.findAll()
        Log.i("InboxViewModel", "fetchFolders $folderList")
    }

    fun deleteFolder(folder: Folder) {
        // Delete folder from the database
        folderRepository.deleteFolder(folder)
        fetchFolders()
    }

    fun insertFolder() {
        val existingFolder = folderRepository.findByName(folderField.value)
        if (existingFolder != null) {
            errorMessage.value = "A folder with the name ${folderField.value} already exists."
        } else {
            val folder = Folder(name = folderField.value, userId =  0)
            folderRepository.insertFolder(folder)
            this.folderField.value = ""
            fetchFolders()
            errorMessage.value = null // Clear the error message when the folder is successfully inserted
        }
    }
}

