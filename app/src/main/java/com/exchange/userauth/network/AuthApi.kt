package com.exchange.userauth.network

import com.exchange.userauth.network.responses.LoginResponse
import com.exchange.userauth.network.responses.registration_response.RegistrationResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthApi {

    @FormUrlEncoded
    @POST("wp-json/rimplenet/v1/users/login")
    suspend fun login(@Field("user_email")userEmail: String,
                      @Field("user_password")password: String): LoginResponse

    @FormUrlEncoded
    @POST("wp-json/rimplenet/v1/users/")
    suspend  fun createUser(
        @Field("first_name") firstName: String,
        @Field("last_name")lastName: String,
        @Field("username") userName: String,
        @Field("user_email")userEmail: String,
        @Field("user_password")password: String): RegistrationResponse
}