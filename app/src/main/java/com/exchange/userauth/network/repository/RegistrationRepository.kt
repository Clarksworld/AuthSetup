package com.exchange.userauth.network.repository

import com.exchange.userauth.data.UserPreferences
import com.exchange.userauth.network.AuthApi

class RegistrationRepository(
    private val api : AuthApi,
): BaseRepository(){


    suspend fun register(
        firstName: String,
        lastName: String,
        userName: String,
        email: String,
        password: String
    ) = safeApiCall{
        api.createUser(firstName, lastName, userName, email, password)

    }
}