package com.devYoussef.cleanarchtest.domain.usecases

import android.util.Patterns
import com.devYoussef.cleanarchtest.presentation.ui.login.LoginValidationResult
import javax.inject.Inject

class LoginUseCase @Inject constructor() {

    operator fun invoke(email: String, password: String): LoginValidationResult {
        if (email.isBlank()) return LoginValidationResult.Error.EmailEmpty("Email is required")
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return LoginValidationResult.Error.EmailInvalid("Invalid email format")

        if (password.isBlank()) return LoginValidationResult.Error.PasswordEmpty("Password is required")
        if (password.length < 6) return LoginValidationResult.Error.PasswordTooShort(
            "Password must be at least 6 characters long"
        )

        if (email != "yoer766@gmail.com" || password != "123456")
            return LoginValidationResult.Error.InvalidCredentials(
                "Invalid email or password"
            )

        return LoginValidationResult.Success
    }
}