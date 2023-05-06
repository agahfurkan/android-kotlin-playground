package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentLoginBinding
import com.agah.furkan.androidplayground.domain.usecase.LoginUseCase
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.util.showLongToast
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel by viewModels<LoginFragmentVM>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        System.loadLibrary("api-keys")
        val someApiKey = getAPIKey()
        Timber.i(someApiKey)
        binding.composeView.setContent {
            AppTheme {
                LoginScreen()
            }
        }
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle, minActiveState = Lifecycle.State.STARTED
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

    external fun getAPIKey(): String

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    fun LoginScreen() {
        val username = viewModel.username
        val password = viewModel.password

        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(id = R.drawable.placeholder_image),
                    "",
                    modifier = Modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp / 2)
                        .aspectRatio(1f)
                )
                OutlinedTextField(value = username,
                    label = { Text(text = stringResource(id = R.string.username)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    onValueChange = {
                        viewModel.username = it
                    })
                OutlinedTextField(value = password,
                    label = { Text(text = stringResource(id = R.string.password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onValueChange = {
                        viewModel.password = it
                    })
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp), onClick = {
                    viewModel.onLoginBtnClicked()
                }) {
                    Text(stringResource(id = R.string.login))
                }
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp), onClick = {
                    navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
                }) {
                    Text(stringResource(id = R.string.register))
                }
            }
        }
    }
}
