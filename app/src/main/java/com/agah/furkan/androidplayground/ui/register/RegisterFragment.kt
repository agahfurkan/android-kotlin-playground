package com.agah.furkan.androidplayground.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.databinding.FragmentRegisterBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.showLongToast
import javax.inject.Inject

class RegisterFragment : BaseFragment(), InjectableFragment {
    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val registerFragmentVM by viewModels<RegisterFragmentVM> { factory }

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
        registerFragmentVM.registerUserResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiSuccessResponse -> {
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                }
                is ApiErrorResponse -> {
                    showLongToast(it.errorMessage)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}