package com.devYoussef.cleanarchtest.common.model.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") val message: String? = null,
    @SerializedName("errors") val errors: Map<String, List<String>>? = null
)
