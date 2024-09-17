package com.david.squid_mail.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.david.squid_mail.model.Email
import com.david.squid_mail.model.EmailPreview
import java.util.Date


class InboxViewModel : ViewModel() {

    val emails = mutableStateListOf(
        Email(
            1,
            "Alice",
            "Meeting Tomorrow",
            "Don't forget our meeting...",
            "Hi Bob, don't forget our meeting tomorrow at 10am. See you there!",
            Date(),
            false,
            false
        ), // Adicione mais e-mails conforme necessário
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
