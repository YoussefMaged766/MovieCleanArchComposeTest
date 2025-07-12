package com.devYoussef.cleanarchtest.presentation.ui.login

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.devYoussef.cleanarchtest.common.network.NetworkMonitor
import com.devYoussef.cleanarchtest.domain.usecases.LoginUseCase
import com.devYoussef.cleanarchtest.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    networkMonitor: NetworkMonitor,
    private val loginUseCase: LoginUseCase
) :
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


    fun onSignInClick() {
        val result = loginUseCase(email = _email.value, password = _password.value)
        viewModelScope.launch {
            when (result) {
                is LoginValidationResult.Success -> {
                    _uiEvent.emit(SignInEvent.NavigateToMain("Welcome!"))
                }

                is LoginValidationResult.Error.EmailEmpty ->
                    _uiEvent.emit(SignInEvent.ShowError("Email is required"))

                is LoginValidationResult.Error.EmailInvalid ->
                    _uiEvent.emit(SignInEvent.ShowError("Invalid email format"))

                is LoginValidationResult.Error.PasswordEmpty ->
                    _uiEvent.emit(SignInEvent.ShowError("Password is required"))

                is LoginValidationResult.Error.PasswordTooShort ->
                    _uiEvent.emit(SignInEvent.ShowError("Password must be at least 6 characters"))

                is LoginValidationResult.Error.InvalidCredentials ->
                    _uiEvent.emit(SignInEvent.ShowError("Incorrect email or password"))
            }
        }

    }

    sealed class SignInEvent {
        data class NavigateToMain(val message: String) : SignInEvent()
        data class ShowError(val message: String) : SignInEvent()

    }

}

sealed class LoginValidationResult {
    data object Success : LoginValidationResult()

    sealed class Error : LoginValidationResult() {
        data object EmailEmpty : Error()
        data object EmailInvalid : Error()
        data object PasswordEmpty : Error()
        data object PasswordTooShort : Error()
        data object InvalidCredentials : Error()
    }
}