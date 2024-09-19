package com.david.squid_mail.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.david.squid_mail.model.RegisterResponse
import com.david.squid_mail.model.UserRegister
import com.david.squid_mail.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel : ViewModel() {
    // Estados dos campos de entrada
    var email by mutableStateOf("")
        internal set
    var name by mutableStateOf("")
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
    fun onNameChange(newName: String) {
        name = newName
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
            try {
                // Obter instância do serviço Retrofit (instancie Retrofit uma vez e reutilize)
                val userService = RetrofitFactory().getUserService()

                // Criar o objeto UserRegister com os dados do usuário
                val call = userService.registerUser(UserRegister(email, email, password))

                // Fazer a chamada usando Retrofit enqueue para chamada assíncrona
                call.enqueue(object : Callback<RegisterResponse> {
                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                        if (response.isSuccessful) {
                            onSuccess()
                        } else {
                            onError("Erro ao cadastrar usuário: ${response.errorBody()?.string() ?: response.message() ?: "Erro desconhecido"}")
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        // Retorna o erro detalhado
                        onError("Falha ao cadastrar usuário: ${t.localizedMessage}")
                    }
                })
            } catch (e: Exception) {
                onError("Erro ao cadastrar usuário: ${e.localizedMessage}")
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