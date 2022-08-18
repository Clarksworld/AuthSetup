package com.exchange.userauth.network

import com.exchange.userauth.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RemoteDataSource {

 companion object{

     private const val BASE_URL = "https://holaprime.com/rimplenet/"

     var token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsb2NhbGhvc3QiLCJpYXQiOjE2NTY0MDk2MTYsImV4cCI6MTY3MjE4OTYxNiwidXNlciI6eyJJRCI6IjUiLCJ1c2VybmFtZSI6InRhaXdvIiwidXNlcl9lbWFpbCI6Im11c3R5eXRlZUBnbWFpbC5jb20iLCJmaXJzdF9uYW1lIjoiVGFpd28iLCJsYXN0X25hbWUiOiJIYXNzYW4iLCJyb2xlcyI6WyJhZG1pbmlzdHJhdG9yIl19fQ.W_4EI_0nWcceTVkcot76xCDhsa0mpfs4s7XBq3FWQ0k"


 }

    fun <Api>buildApi(
        api: Class<Api>,
        authToken: String? = null
    ): Api{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also {client ->

                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }


    fun <Api>createUserBuildApi(
        api: Class<Api>,
    ): Api{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor {chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Authorization","Bearer $token")
                    }.build())
                }.also {client ->

                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}