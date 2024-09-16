package com.david.squid_mail.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.david.squid_mail.model.Email
import com.david.squid_mail.model.EmailPreview


class InboxViewModel : ViewModel() {

    val emails = mutableStateListOf(
        Email(1, "Alice", "Meeting Tomorrow", "Don't forget our meeting...", time = "21:45"),
        Email(2, "Bob", "Weekly Report", "Please find attached...", time = "21:45"),
        Email(3, "Charlie", "Lunch Invitation", "How about lunch tomorrow?...", time = "21:45")
        // Adicione mais e-mails conforme necessário
    )

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
}
