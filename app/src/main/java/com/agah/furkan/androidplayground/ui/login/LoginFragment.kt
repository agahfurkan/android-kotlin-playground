package com.agah.furkan.androidplayground.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.domain.usecase.LoginUseCase
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.main.MainFragmentDirections
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.util.launchAndCollectIn
import com.agah.furkan.androidplayground.util.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment(null) {
    private val viewModel by viewModels<LoginFragmentVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        System.loadLibrary("api-keys")
        val someApiKey = getAPIKey()
        Timber.i(someApiKey)
        initObservers()
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                LoginScreen(viewModel = viewModel)
            }
        }
    }

    private fun initObservers() {
        initLoginObserver()
        initRegisterObserver()
    }

    private fun initLoginObserver() {
        viewModel.loginState.launchAndCollectIn(viewLifecycleOwner) { state ->
            when (state) {
                is LoginUseCase.UiState.Success -> {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
                }

                is LoginUseCase.UiState.Failure -> {
                    showLongToast(state.failureMessage)
                }

                LoginUseCase.UiState.Loading -> Timber.i(state.toString())
            }
        }
    }

    private fun initRegisterObserver() {
        viewModel.uiEvent.launchAndCollectIn(viewLifecycleOwner) { event ->
            when (event) {
                is LoginScreenEvent.NavigateToRegisterScreen -> {
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
                }
            }
        }
    }

    external fun getAPIKey(): String
}


@Composable
fun LoginScreen(viewModel: LoginFragmentVM) {
    LoginFormContent(
        username = viewModel.username,
        password = viewModel.password,
        onUsernameChanged = {
            viewModel.username = it
        },
        onPasswordChanged = {
            viewModel.password = it
        },
        onLoginButtonChanged = viewModel::onLoginBtnClicked,
        onRegisterButtonChanged = viewModel::onRegisterButtonClicked
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginFormContent(
    username: String,
    password: String,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonChanged: () -> Unit,
    onRegisterButtonChanged: () -> Unit
) {
    AppTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 36.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
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
                        onUsernameChanged(it)
                    })
                OutlinedTextField(value = password,
                    label = { Text(text = stringResource(id = R.string.password)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    onValueChange = {
                        onPasswordChanged(it)
                    })
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp), onClick = {
                    onLoginButtonChanged()
                }) {
                    Text(stringResource(id = R.string.login))
                }
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp), onClick = {
                    onRegisterButtonChanged()
                }) {
                    Text(stringResource(id = R.string.register))
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginFormContentPreview() {
    LoginFormContent(
        username = "Dusty Fox",
        password = "eleifend",
        onUsernameChanged = {},
        onPasswordChanged = {},
        onLoginButtonChanged = {},
        onRegisterButtonChanged = {})
}
