package com.exchange.userauth.ui.auth.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exchange.userauth.network.Resource
import com.exchange.userauth.network.repository.AuthRepository
import com.exchange.userauth.network.repository.RegistrationRepository
import com.exchange.userauth.network.responses.LoginResponse
import com.exchange.userauth.network.responses.registration_response.RegistrationResponse
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val repository: RegistrationRepository
):
    ViewModel(){

    private val _registrationResponse: MutableLiveData<Resource<RegistrationResponse>> = MutableLiveData()
    val registrationResponse: LiveData<Resource<RegistrationResponse>> get() = _registrationResponse

    var token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsb2NhbGhvc3QiLCJpYXQiOjE2NTY0MDk2MTYsImV4cCI6MTY3MjE4OTYxNiwidXNlciI6eyJJRCI6IjUiLCJ1c2VybmFtZSI6InRhaXdvIiwidXNlcl9lbWFpbCI6Im11c3R5eXRlZUBnbWFpbC5jb20iLCJmaXJzdF9uYW1lIjoiVGFpd28iLCJsYXN0X25hbWUiOiJIYXNzYW4iLCJyb2xlcyI6WyJhZG1pbmlzdHJhdG9yIl19fQ.W_4EI_0nWcceTVkcot76xCDhsa0mpfs4s7XBq3FWQ0k"


    fun  register(
        firstName: String,
        lastName: String,
        userName: String,
        email: String,
        password: String
    ) = viewModelScope.launch {

        _registrationResponse.value = repository.register(firstName, lastName, userName, email, password)
    }

//    fun saveUserId(token: String) = viewModelScope.launch {
//        repository.saveUserId(token)
//    }


}