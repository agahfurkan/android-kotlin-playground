package com.agah.furkan.androidplayground.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.databinding.FragmentRegisterBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.showLongToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!
    private val registerFragmentVM by viewModels<RegisterFragmentVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.registerSubmitButton.setOnClickListener {
            registerFragmentVM.registerNewUser(
                UserRegisterBody(
                    username = binding.registerUsername.text.toString(),
                    password = binding.registerPassword.text.toString()
                )
            )
        }
    }

    private fun initObservers() {
        registerFragmentVM.registerUserResponse.observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    showLongToast(apiResponse.data.message.toString())
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                }
                is ApiErrorResponse -> {
                    showLongToast(apiResponse.errorMessage)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}