package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.databinding.FragmentLoginBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.SharedPrefUtil
import com.agah.furkan.androidplayground.util.showLongToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment(), View.OnClickListener {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!
    private val loginFragmentVM by viewModels<LoginFragmentVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}