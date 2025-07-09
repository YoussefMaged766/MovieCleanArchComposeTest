package com.devYoussef.cleanarchtest.presentation.ui.login

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import com.devYoussef.cleanarchtest.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(networkMonitor: NetworkMonitor) :
    BaseViewModel(networkMonitor) {

    private val _uiEvent = MutableSharedFlow<SignInEvent>()
    val uiEvent: SharedFlow<SignInEvent> = _uiEvent


    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun clearEmail() {
        _email.value = ""
    }


    fun onSignInSuccess(message: String) {
        viewModelScope.launch {
            _uiEvent.emit(SignInEvent.NavigateToMain(message))
        }
    }

    fun onSignInError(message: String) {
        viewModelScope.launch {
            _uiEvent.emit(SignInEvent.ShowError(message))
        }
    }

    fun onValidationError(emailError: String?, passwordError: String?) {
        viewModelScope.launch {
            _uiEvent.emit(SignInEvent.ValidationError(emailError, passwordError))
        }
    }

    private fun validateEmail(email: String): String? {
        return when {
            email.isBlank() -> "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Invalid email format"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "Password is required"
            password.length < 6 -> "Password must be at least 6 characters"
            else -> null
        }
    }

    fun onSignInClick() {
        val emailError = validateEmail(email.value)
        val passwordError = validatePassword(password.value)

        if (emailError != null || passwordError != null) {
            // Validation failed
            onValidationError(emailError, passwordError)
        } else if (email.value == "yoer766@gmail.com" && password.value == "123456") {
            // Validation passed and credentials are correct
            onSignInSuccess("Sign-in successful")
        } else {
            // Validation passed but credentials are wrong
            onSignInError("Incorrect email or password.")
        }
    }

    sealed class SignInEvent {
        data class NavigateToMain(val message: String) : SignInEvent()
        data class ShowError(val message: String) : SignInEvent()
        data class ValidationError(val emailError: String?, val passwordError: String?) :
            SignInEvent()
    }
}