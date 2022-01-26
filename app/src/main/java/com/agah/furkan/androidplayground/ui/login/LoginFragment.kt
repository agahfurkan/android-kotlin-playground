package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentLoginBinding
import com.agah.furkan.androidplayground.domain.usecase.LoginUseCase
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.showLongToast
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login), View.OnClickListener {
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val loginFragmentVM by viewModels<LoginFragmentVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        System.loadLibrary("api-keys")
        val someApiKey = getAPIKey()
        Timber.i(someApiKey)
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
            loginFragmentVM.loginState.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect { state ->
                when (state) {
                    is LoginUseCase.UiState.Success -> {
                        navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                    }
                    is LoginUseCase.UiState.Failure -> {
                        showLongToast(state.failureMessage)
                    }
                    LoginUseCase.UiState.Loading -> Timber.i(state.toString())
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
