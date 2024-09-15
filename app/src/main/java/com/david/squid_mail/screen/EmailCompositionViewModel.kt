package com.david.squid_mail.screen

import android.content.Context
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.squid_mail.database.repository.EmailRepository
import com.david.squid_mail.model.Email
import kotlinx.coroutines.launch

class EmailCompositionViewModel(context: Context) : ViewModel() {


    val emailRepository = EmailRepository(context)
    // Mutable state to hold the recipient's email address

    val emailSender = "example@gmail.com"
    var emailRecipient by mutableStateOf("")
        private set

    // Mutable state to hold the email subject
    var emailSubject by mutableStateOf("")
        private set

    // Mutable state to hold the email body content
    var emailBody by mutableStateOf("")
        private set

    // Mutable state to hold an error message if the recipient's email is invalid
    var emailRecipientError by mutableStateOf("")
        private set

    // Mutable state to hold an error message if the email subject is blank
    var emailSubjectError by mutableStateOf("")
        private set

    // Function to update the recipient's email and validate its format
    fun updateRecipient(newRecipient: String) {
        emailRecipient = newRecipient
        emailRecipientError = if (isValidEmail(newRecipient)) "" else "Formato de e-mail inválido" // Checks if the email is valid, otherwise sets an error message
    }

    // Function to update the email subject and check if it's not blank
    fun updateSubject(newSubject: String) {
        emailSubject = newSubject
        emailSubjectError = if (newSubject.isNotBlank()) "" else "Assunto é obrigatório" // Ensures the subject is not empty
    }

    // Function to update the email body content
    fun updateBody(newBody: String) {
        emailBody = newBody
    }

    // Function to send the email, first validating the input fields
    fun sendEmail() {
        if (validateEmailFields()) {
            // Launches a coroutine to handle the email sending process asynchronously
            viewModelScope.launch {
                // Logic to send the email would be placed here
                emailRepository.insertEmail( Email(
                    sender = emailSender,
                    subject = emailSubject,
                    preview = emailBody,
                    time = "now",
//                    recipient = emailRecipient,
                ))
            }
        }
    }

    // Function to save the current email as a draft
    fun saveDraft() {
        // Launches a coroutine to handle the draft saving process asynchronously
        viewModelScope.launch {
            // Logic to save the draft would be placed here
        }
    }

    // Function to validate the email fields (recipient and subject)
    private fun validateEmailFields(): Boolean {
        var isValid = true

        // Checks if the recipient's email is valid
        if (!isValidEmail(emailRecipient)) {
            emailRecipientError = "Formato de e-mail inválido" // Sets an error message if invalid
            isValid = false
        }

        // Checks if the email subject is not blank
        if (emailSubject.isBlank()) {
            emailSubjectError = "Assunto é obrigatório" // Sets an error message if blank
            isValid = false
        }

        return isValid
    }

    // Function to check if an email address is in a valid format
    private fun isValidEmail(email: String): Boolean {
//        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() // Uses Android's pattern matcher to validate the email format
        return true
    }
}
