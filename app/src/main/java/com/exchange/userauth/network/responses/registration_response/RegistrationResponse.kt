package com.exchange.userauth.network.responses.registration_response

data class RegistrationResponse(
    val `data`: Data,
    val error: List<Any>,
    val message: String,
    val status: Boolean,
    val status_code: Int
)