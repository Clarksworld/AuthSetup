package com.exchange.userauth.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exchange.userauth.network.repository.AuthRepository
import com.exchange.userauth.network.repository.BaseRepository
import com.exchange.userauth.network.repository.RegistrationRepository
import com.exchange.userauth.ui.auth.AuthViewModel
import com.exchange.userauth.ui.auth.registration.RegistrationViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
    ): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T   {

       return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T

           modelClass.isAssignableFrom(RegistrationViewModel::class.java) -> RegistrationViewModel(repository as RegistrationRepository) as T

            else -> throw IllegalArgumentException("ViewModelClass Not Fount")
        }

    }

}