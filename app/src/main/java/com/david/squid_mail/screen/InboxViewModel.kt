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
import com.david.squid_mail.model.Email
import com.david.squid_mail.model.EmailPreview
import java.util.Date


class InboxViewModel(context: Context) : ViewModel() {

    val emailRepository = EmailRepository(context)
    var _emails = MutableLiveData<List<Email>>()
    val emailPreviews: LiveData<List<EmailPreview>> = _emails.map { emails ->
        emails.map { EmailPreview(it) }
    }

    val isSelectionMode = mutableStateOf(false)
    val selectedEmails = mutableStateListOf<EmailPreview>()

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
        val emailList = emailRepository.findAllToInbox()
        _emails.value = emailList
        Log.i("InboxViewModel", "fetchEmails" + _emails.value?.size)
    }

    fun archiveSelectedEmails() {
        selectedEmails.forEach { preview ->
            preview.email.isArchived = true
            emailRepository.updateEmail(preview.email) // Atualiza o email no banco de dados
        }
        cancelSelection()
    }
}
