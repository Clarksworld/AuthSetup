package com.exchange.userauth.ui.auth.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.exchange.userauth.R
import com.exchange.userauth.databinding.FragmentRegistratonBinding
import com.exchange.userauth.network.AuthApi
import com.exchange.userauth.network.Resource
import com.exchange.userauth.network.repository.AuthRepository
import com.exchange.userauth.network.repository.RegistrationRepository
import com.exchange.userauth.ui.base.BaseFragment
import com.exchange.userauth.ui.enable
import com.exchange.userauth.ui.home.HomeActivity
import com.exchange.userauth.ui.startNewActivity
import com.exchange.userauth.ui.visible

class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistratonBinding, RegistrationRepository>(){





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.accountCreationProgress.visible(false)
        binding.createAccountButton.enable(true)

        viewModel.registrationResponse.observe(viewLifecycleOwner) {

            binding.accountCreationProgress.visible(false)
            when (it) {

                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()

                    var id = it.value.data.id
                    binding.createAccountMessage.text = id.toString()

                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)

                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

//                is Resource.Loading -> {
//                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
//                }
            }
        }

//        binding.appEnterPasswordEd.addTextChangedListener {
//
//            val firstName = binding.appEnterFirstNameEd.text.toString().trim()
//            val lastName = binding.appEnterFirstNameEd.text.toString().trim()
//            val userName = binding.appEnterFirstNameEd.text.toString().trim()
//            val email = binding.appEnterFirstNameEd.text.toString().trim()
//
//            binding.createAccountButton.enable(
//                firstName.isNotEmpty() &&
//                    lastName.isNotEmpty() &&
//                    userName.isNotEmpty()&&
//                    email.isNotEmpty()&&
//                    it.toString().isNotEmpty())
//        }

        binding.createAccountButton.setOnClickListener {

//            binding.accountCreationProgress.visible(true)
//            val firstName = binding.appEnterFirstNameEd.text.toString().trim()
//            val lastName = binding.appEnterFirstNameEd.text.toString().trim()
//            val userName = binding.appEnterFirstNameEd.text.toString().trim()
//            val email = binding.appEnterFirstNameEd.text.toString().trim()
//            val password = binding.appEnterPasswordEd.text.toString().trim()

            viewModel.register("wmnKerrery", "nhpqqete", "kpetiutte",
                "rtkatefcrrypate@gmail.com", "fdkerrkky123pete")

        }

        binding.registrationInstructionLayout.setOnClickListener {

            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }

    override fun getViewModel() = RegistrationViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegistratonBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): RegistrationRepository {


        return RegistrationRepository(remoteDataSource.createUserBuildApi(AuthApi::class.java))
    }

//    override fun getFragmentRepository() =
//        AuthRepository(remoteDataSource.createUserBuildApi(AuthApi::class.java), userPreference)


}