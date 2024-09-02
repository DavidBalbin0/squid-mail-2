package com.david.squid_mail.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var emailError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    var passwordVisible by mutableStateOf(false)
        private set

    fun onEmailChange(newEmail: String) {
        email = newEmail
        emailError = if (isValidEmail(newEmail)) "" else "Formato de e-mail inválido"
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        passwordError = if (isValidPassword(newPassword)) "" else "A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, um número e um caractere especial."
    }

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        val passwordMatcher = Regex(passwordPattern)
        return passwordMatcher.matches(password)
    }

    // Simulated authentication function
    private suspend fun authenticateUser(email: String, password: String): Boolean {
        // Replace this with your actual authentication logic
        return email == "test@example.com" && password == "Test@1234"
    }

    fun login() {
        // Reset error messages
        emailError = ""
        passwordError = ""

        // Validate email and password
        if (!isValidEmail(email)) {
            emailError = "Formato de e-mail inválido"
        }
        if (!isValidPassword(password)) {
            passwordError = "A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, um número e um caractere especial."
        }

        if (emailError.isEmpty() && passwordError.isEmpty()) {
            // Execute login logic
            viewModelScope.launch {
                // Example login logic, replace with actual authentication
                val result = authenticateUser(email, password)
                if (result) {
                    // Navigate to the next screen or handle successful login
                } else {
                    // Show login error
                    emailError = "Falha na autenticação. Verifique suas credenciais."
                }
            }
        }
    }
}