package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentLoginBinding
import com.agah.furkan.androidplayground.domain.Result
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
        Log.i(Companion.TAG, someApiKey)
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
            ).collect { result ->
                when (result) {
                    is Result.Success -> {
                        SharedPrefUtil.setToken(result.data.token)
                        SharedPrefUtil.setUserid(result.data.userId)
                        navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                    }
                    is Result.Failure -> {
                        showLongToast(result.error.errorMessage)
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

    companion object {
        private const val TAG: String = "LoginFragment"
    }
}
