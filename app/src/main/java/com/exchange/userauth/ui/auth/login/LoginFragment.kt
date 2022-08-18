package com.exchange.userauth.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.exchange.userauth.R
import com.exchange.userauth.databinding.FragmentLoginBinding
import com.exchange.userauth.network.AuthApi
import com.exchange.userauth.network.Resource
import com.exchange.userauth.network.repository.AuthRepository
import com.exchange.userauth.ui.auth.AuthViewModel
import com.exchange.userauth.ui.base.BaseFragment
import com.exchange.userauth.ui.enable
import com.exchange.userauth.ui.home.HomeActivity
import com.exchange.userauth.ui.startNewActivity
import com.exchange.userauth.ui.visible
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.loginProgress.visible(false)
        binding.loginButton.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {

            binding.loginProgress.visible(false)
            when(it) {

                is Resource.Success ->{
                    Toast.makeText(requireContext(), it.toString() , Toast.LENGTH_SHORT).show()

                        viewModel.saveAuthToken(it.value.data.access_token)
                        requireActivity().startNewActivity(HomeActivity::class.java)


                }

                is Resource.Error ->{
                    Toast.makeText(requireContext(), " Login Failure ", Toast.LENGTH_SHORT).show()
                }

//                is Resource.Loading -> {
//                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
//                }
            }
        })

        binding.enterPasswordEd.addTextChangedListener{

           val email =  binding.enterEmailEd.text.toString().trim()
            binding.loginButton.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.loginButton.setOnClickListener {

            binding.loginProgress.visible(true)
            val email = binding.enterEmailEd.text.toString().trim()
            val password = binding.enterPasswordEd.text.toString().trim()

            viewModel.login(email, password)

        }

        binding.registrationInstructionLayout.setOnClickListener {

            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }


    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreference)


}