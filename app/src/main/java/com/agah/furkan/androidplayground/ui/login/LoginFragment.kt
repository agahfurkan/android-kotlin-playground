package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.data.web.model.request.UserLoginBody
import com.agah.furkan.androidplayground.databinding.FragmentLoginBinding
import com.agah.furkan.androidplayground.di.InjectableFragment
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment(), InjectableFragment, View.OnClickListener {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val loginFragmentVM by viewModels<LoginFragmentVM> { factory }

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