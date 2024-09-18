package com.david.squid_mail.screen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.david.squid_mail.database.repository.EmailRepository
import com.david.squid_mail.database.repository.FolderRepository
import com.david.squid_mail.model.Email
import com.david.squid_mail.model.EmailPreview
import com.david.squid_mail.model.Folder
import com.david.squid_mail.model.FolderType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class EmailViewModel(context: Context, folderType: FolderType = FolderType.INBOX, folderId: Long? = null) : ViewModel() {

    val emailRepository = EmailRepository(context)
    val folderRepository = FolderRepository(context)
    var _emails = MutableLiveData<List<Email>>()
    val emailPreviews: LiveData<List<EmailPreview>> = _emails.map { emails ->
        emails.map { EmailPreview(it) }
    }
    val isSelectionMode = mutableStateOf(false)
    val selectedEmails = mutableStateListOf<EmailPreview>()

    var _folderList = MutableStateFlow<List<Folder>>(emptyList())
    val folderList: StateFlow<List<Folder>> = _folderList

    val folderType = folderType
    val folderId = folderId
    init {
        fetchFolders()
    }



    fun toggleSelection(email: EmailPreview) {
        email.isSelected.value = !email.isSelected.value
        if (email.isSelected.value) {
            selectedEmails.add(email)
        } else {
            selectedEmails.remove(email)
        }
    }

    fun cancelSelection() {
        isSelectionMode.value = false
        selectedEmails.forEach { it.isSelected.value = false }
        selectedEmails.clear()

    }

    fun enterSelectionMode() {
        isSelectionMode.value = true
    }

    fun fetchEmails() {
        val emailList = emailRepository.findAllByFolderType(folderType, folderId )
        _emails.value = emailList
        Log.i("InboxViewModel", "fetchEmails" + _emails.value?.size)
    }

    fun fetchFolders() {
        _folderList.value = folderRepository.findAll()
        Log.i("InboxViewModel", "fetchFolders in email screen $folderList")
    }


    fun archiveSelectedEmails() {
        selectedEmails.forEach { preview ->
            preview.email.isArchived = true
            emailRepository.updateEmail(preview.email)
        }
        cancelSelection()
    }

    fun markAsReadSelectedEmails() {
        selectedEmails.forEach { preview ->
            preview.email.isRead = true
            emailRepository.updateEmail(preview.email)
        }
        cancelSelection()
    }

    fun toggleFavorite(email: Email) {
        email.isFavorite = !email.isFavorite
        emailRepository.updateEmail(email) // Atualiza o email no banco de dados
    }

    fun moveToFolderSelectedEmails(folderId: Long) {
        selectedEmails.forEach { preview ->
            preview.email.folderId = folderId
            emailRepository.updateEmail(preview.email)
        }
    }
}
