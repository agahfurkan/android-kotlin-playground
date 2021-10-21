package com.agah.furkan.androidplayground.ui.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.data.web.model.ApiErrorResponse
import com.agah.furkan.androidplayground.data.web.model.ApiSuccessResponse
import com.agah.furkan.androidplayground.data.web.model.request.UserRegisterBody
import com.agah.furkan.androidplayground.databinding.FragmentRegisterBinding
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.util.showLongToast
import com.agah.furkan.androidplayground.util.textValue
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {
    override val toolbarType: ToolbarType
        get() = ToolbarType.WithActionButtons(listOf(ToolbarType.ToolbarButton.BACK))
    private val binding by viewBinding(FragmentRegisterBinding::bind)
    private val registerFragmentVM by viewModels<RegisterFragmentVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.registerBtnRegister.setOnClickListener {
            registerFragmentVM.registerNewUser(
                UserRegisterBody(
                    username = binding.registerEtUsername.textValue,
                    password = binding.registerEtPassword.textValue
                )
            )
        }
    }

    private fun initObservers() {
        registerFragmentVM.registerUserResponse.observe(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is ApiSuccessResponse -> {
                    showLongToast(apiResponse.data.message.toString())
                    navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                }
                is ApiErrorResponse -> {
                    showLongToast(apiResponse.errorMessage)
                }
            }
        }
    }
}
