package com.david.squid_mail.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmailReadViewModel : ViewModel() {

    var emailSubject by mutableStateOf("")
        private set

    var emailSender by mutableStateOf("")
        private set

    var emailRecipients by mutableStateOf("")
        private set

    var emailCcRecipients by mutableStateOf("")
        private set

    var emailReceivedDate by mutableStateOf("")
        private set

    var emailBody by mutableStateOf("")
        private set

    var isFavorite by mutableStateOf(false)
        private set

    var isUnread by mutableStateOf(false)
        private set

    fun loadEmail(emailId: String) {
        viewModelScope.launch {
            // Load the email details using the emailId
            // This is where you'd retrieve the email data from a repository
            // For now, let's assume the data is fetched and set the state
            emailSubject = "Sample Subject"
            emailSender = "sender@example.com"
            emailRecipients = "recipient@example.com"
            emailCcRecipients = "cc@example.com"
            emailReceivedDate = "2024-09-02 10:00 AM"
            emailBody = "This is the body of the email..."
            isUnread = false
        }
    }

    fun markAsFavorite() {
        isFavorite = !isFavorite
        // Handle saving this state in the database or server
    }

    fun markAsUnread() {
        isUnread = true
        // Handle updating the email status in the database or server
    }

    fun moveToFolder(folderName: String) {
        viewModelScope.launch {
            // Handle moving the email to the specified folder
        }
    }

    fun deleteEmail() {
        viewModelScope.launch {
            // Handle deleting the email
        }
    }

    fun addToCalendar() {
        viewModelScope.launch {
            // Handle creating a calendar event based on email content
        }
    }

    fun replyToEmail() {
        // Handle navigating to the reply screen with pre-filled data
    }
}
