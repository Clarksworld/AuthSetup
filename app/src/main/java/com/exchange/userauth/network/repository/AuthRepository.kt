package com.exchange.userauth.network.repository

import com.exchange.userauth.data.UserPreferences
import com.exchange.userauth.network.AuthApi

class AuthRepository (
    private val api : AuthApi,
    private val userPreferences: UserPreferences
        ): BaseRepository(){

            suspend fun login(
                email: String,
                password: String
            ) = safeApiCall{
                api.login(email, password)

            }


    suspend fun register(
        firstName: String,
        lastName: String,
        userName: String,
        email: String,
        password: String
    ) = safeApiCall{
        api.createUser(firstName, lastName, userName, email, password)

    }


    suspend fun saveAuthToken(token: String){
        userPreferences.safeAuthToken(token)
    }

    suspend fun saveUserId(id: String){
        userPreferences.saveUserId(id)
    }

    suspend fun userToken(myToken: String){
        userPreferences.registrationToken(myToken)
    }
}