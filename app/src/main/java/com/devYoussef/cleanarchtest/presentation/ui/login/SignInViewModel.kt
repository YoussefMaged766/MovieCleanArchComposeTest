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


    private val _email = MutableStateFlow("yoer766@gmail.com")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("123456")
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
            if (result is LoginValidationResult.Error) {
                _uiEvent.emit(SignInEvent.ShowError(result.message))
            } else if (result is LoginValidationResult.Success) {
                _uiEvent.emit(SignInEvent.NavigateToMain("Welcome!"))
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

    sealed class Error(val message: String) : LoginValidationResult() {
        data class EmailEmpty(val error: String) : Error(error)
        data class EmailInvalid(val error: String) : Error(error)
        data class PasswordEmpty(val error: String) : Error(error)
        data class PasswordTooShort(val error: String) : Error(error)
        data class InvalidCredentials(val error: String) : Error(error)
    }
}