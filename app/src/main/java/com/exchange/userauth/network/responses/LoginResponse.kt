package com.exchange.userauth.network.responses

data class LoginResponse(
    val `data`: Data,
    val error: List<Any>,
    val message: String,
    val status: Boolean,
    val status_code: Int
)