package com.david.squid_mail.screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    // Estados dos campos de entrada
    var email by mutableStateOf("")
        internal set

    var password by mutableStateOf("")
        internal set

    var confirmPassword by mutableStateOf("")
        internal set

    var passwordVisible by mutableStateOf(false)
        private set

    // Estados das mensagens de erro
    var emailError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    var confirmPasswordError by mutableStateOf("")
        private set

    // Estado para habilitar/desabilitar o botão de cadastro
    var isFormValid by mutableStateOf(false)
        internal set

    // Funções para atualizar os campos
    fun onEmailChange(newEmail: String) {
        email = newEmail
        validateEmail()
        validateForm()
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        validatePassword()
        validateConfirmPassword()
        validateForm()
    }

    fun onConfirmPasswordChange(newConfirmPassword: String) {
        confirmPassword = newConfirmPassword
        validateConfirmPassword()
        validateForm()
    }

    // Função para alternar a visibilidade da senha
    fun onPasswordVisibilityChange() {
        passwordVisible = !passwordVisible
    }

    // Funções de validação
    private fun validateEmail() {
        emailError = if (isValidEmail(email)) "" else "Formato de e-mail inválido"
    }

    private fun validatePassword() {
        passwordError = if (isValidPassword(password)) "" else "A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula, um número e um caractere especial."
    }

    private fun validateConfirmPassword() {
        confirmPasswordError = if (confirmPassword == password) "" else "As senhas não coincidem."
    }

    private fun validateForm() {
        isFormValid = isValidEmail(email) &&
                isValidPassword(password) &&
                (confirmPassword == password)
    }

    // Função para submeter o formulário
    fun submitForm(onSuccess: () -> Unit, onError: (String) -> Unit) {
        if (isFormValid) {
            // Implementar a lógica de cadastro, como chamada a um repositório ou serviço de API
            viewModelScope.launch {
                try {
                    // Simulação de cadastro bem-sucedido
                    // Substitua pela lógica real de cadastro
                    // e.g., repository.register(email, password)
                    onSuccess()
                } catch (e: Exception) {
                    onError(e.message ?: "Erro desconhecido")
                }
            }
        } else {
            onError("Por favor, corrija os erros no formulário.")
        }
    }

    // Funções de validação
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        // Expressão regular para verificar os requisitos da senha
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        return Regex(passwordPattern).matches(password)
    }
}