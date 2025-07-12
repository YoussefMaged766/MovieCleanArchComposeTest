package com.devYoussef.cleanarchtest.domain.usecases

import android.util.Patterns
import com.devYoussef.cleanarchtest.presentation.ui.login.LoginValidationResult
import javax.inject.Inject

class LoginUseCase @Inject constructor() {

    operator fun invoke(email: String, password: String): LoginValidationResult {
        if (email.isBlank()) return LoginValidationResult.Error.EmailEmpty
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return LoginValidationResult.Error.EmailInvalid

        if (password.isBlank()) return LoginValidationResult.Error.PasswordEmpty
        if (password.length < 6) return LoginValidationResult.Error.PasswordTooShort

        if (email != "yoer766@gmail.com" || password != "123456")
            return LoginValidationResult.Error.InvalidCredentials

        return LoginValidationResult.Success
    }
}