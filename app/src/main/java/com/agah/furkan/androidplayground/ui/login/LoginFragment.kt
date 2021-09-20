package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.databinding.FragmentLoginBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import com.agah.furkan.androidplayground.util.showLongToast
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login), View.OnClickListener {
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val loginFragmentVM by viewModels<LoginFragmentVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listOf(
            binding.loginButton,
            binding.registerButton
        ).forEach {
            it.setOnClickListener(this)
        }
        initObservers()
    }

    private fun initObservers() {
        loginFragmentVM.loginResponse.observe(viewLifecycleOwner) {
            when (it) {
                is ApiSuccessResponse -> {
                    if (it.data.isSuccess) {
                        SharedPrefUtil.setToken(it.data.token!!)
                        SharedPrefUtil.setUserid(it.data.userId!!)
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                    } else {
                        showLongToast(it.data.message.toString())
                    }
                }
                is ApiErrorResponse -> {
                    showLongToast(it.errorMessage)
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.loginButton -> {
                loginFragmentVM.login(
                    UserLoginBody(
                        username = binding.loginUsername.text.toString(),
                        password = binding.loginPassword.text.toString()
                    )
                )
            }
            binding.registerButton -> {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }
}
