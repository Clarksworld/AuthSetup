package com.exchange.userauth.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    context: Context
) {


    var token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsb2NhbGhvc3QiLCJpYXQiOjE2NTY0MDk2MTYsImV4cCI6MTY3MjE4OTYxNiwidXNlciI6eyJJRCI6IjUiLCJ1c2VybmFtZSI6InRhaXdvIiwidXNlcl9lbWFpbCI6Im11c3R5eXRlZUBnbWFpbC5jb20iLCJmaXJzdF9uYW1lIjoiVGFpd28iLCJsYXN0X25hbWUiOiJIYXNzYW4iLCJyb2xlcyI6WyJhZG1pbmlzdHJhdG9yIl19fQ.W_4EI_0nWcceTVkcot76xCDhsa0mpfs4s7XBq3FWQ0k"

    private val applicationContext = context.applicationContext

    private val dataStore: DataStore<Preferences>

    init {

        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )


    }

    val authToken: Flow<String?>


    get() = dataStore.data.map { prefereces ->
        prefereces[KEY_AUTH]
    }

    val userId: Flow<String?>
    get() = dataStore.data.map { preference->
        preference[KEY_USERID]
    }

    val userToken: Flow<String?>
        get() = dataStore.data.map { preference->
            preference[KEY_USERTOKEN]
        }


    suspend fun safeAuthToken(authToken: String){
        dataStore.edit { preference ->

            preference[KEY_AUTH] = authToken
        }

    }

    suspend fun saveUserId(authToken: String){
        dataStore.edit { preference ->

            preference[KEY_AUTH] = authToken
        }

    }

    suspend fun registrationToken(myToken:String){
        dataStore.edit { preference ->

            preference[KEY_USERTOKEN] = myToken
        }

    }

    companion object{

        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val KEY_USERID = preferencesKey<String>("key_userid")
        private val KEY_USERTOKEN = preferencesKey<String>("key_token")




    }

}