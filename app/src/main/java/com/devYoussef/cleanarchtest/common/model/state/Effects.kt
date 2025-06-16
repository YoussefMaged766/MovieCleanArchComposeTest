package com.devYoussef.cleanarchtest.common.model.state

sealed class UiEffect {
    data class ShowSnackbar(
        val message: String,
        val action: String? = null,
        val onAction: (() -> Unit)? = null
    ) : UiEffect()

}