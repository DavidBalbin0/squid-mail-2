package com.david.squid_mail.screen

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.david.squid_mail.model.LoginResponse
import com.david.squid_mail.model.UserLogin
import com.david.squid_mail.service.RetrofitFactory
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel() : ViewModel() {
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

    var loginErrorMessage by mutableStateOf("")

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

    fun login(onSuccess: (LoginResponse) -> Unit, onError: (String) -> Unit, context: Context){
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

                try {
                    val userService = RetrofitFactory().getUserService()
                    val call = userService.loginUser(UserLogin(email, password))
                    call.enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            if (response.isSuccessful && response.body() != null) {
                                loginErrorMessage= response.body().toString()
                                val loginResponse = response.body()!!
                                val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("jwt_token", loginResponse.token)
                                editor.apply()
                                onSuccess(loginResponse)
                            } else {
                                // Handle login error
                                loginErrorMessage = "Falha na autenticação. Verifique suas credenciais."
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            // Handle network error
                            loginErrorMessage = "Erro de rede. Verifique sua conexão."
                        }
                    })
                }catch (e: Exception) {
                    onError("Erro inesperado: ${e.message}")
                }
            }
        }
    }
}