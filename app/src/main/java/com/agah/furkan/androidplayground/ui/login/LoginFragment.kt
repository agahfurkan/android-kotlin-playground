package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.databinding.FragmentLoginBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import com.agah.furkan.androidplayground.util.showLongToast
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login), View.OnClickListener {
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val loginFragmentVM by viewModels<LoginFragmentVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        System.loadLibrary("api-keys")
        val someApiKey = getAPIKey()
        binding.viewModel = loginFragmentVM
        listOf(
            binding.loginBtnRegister
        ).forEach {
            it.setOnClickListener(this)
        }
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            loginFragmentVM.loginResponse.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect { response ->
                when (response) {
                    is ApiSuccessResponse -> {
                        if (response.data.isSuccess) {
                            SharedPrefUtil.setToken(response.data.token!!)
                            SharedPrefUtil.setUserid(response.data.userId!!)
                            navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                        } else {
                            showLongToast(response.data.message.toString())
                        }
                    }
                    is ApiErrorResponse -> {
                        showLongToast(response.errorMessage)
                    }
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.loginBtnRegister -> {
                navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }

    external fun getAPIKey(): String
}
