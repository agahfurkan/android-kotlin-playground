package com.agah.furkan.androidplayground.ui.login

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.domain.usecase.LoginUseCase
import com.agah.furkan.ui.theme.AppTheme
import com.agah.furkan.androidplayground.util.ext.showToast
import com.agah.furkan.androidplayground.util.launchAndCollectIn
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginScreenVM = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    val lifeCycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.loginState.launchAndCollectIn(lifeCycleOwner) { state ->
            when (state) {
                is LoginUseCase.UiState.Success -> {
                    onLoginSuccess()
                }

                is LoginUseCase.UiState.Failure -> {
                    context.showToast(state.failureMessage)
                }

                LoginUseCase.UiState.Loading -> Timber.i(state.toString())
            }
        }
        viewModel.uiEvent.launchAndCollectIn(lifeCycleOwner) { event ->
            when (event) {
                is LoginScreenEvent.NavigateToRegisterScreen -> {
                    onRegisterClicked()
                }
            }
        }
    }
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
    modifier: Modifier = Modifier,
    username: String,
    password: String,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonChanged: () -> Unit,
    onRegisterButtonChanged: () -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(all = 36.dp),
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

@Preview
@Composable
fun LoginFormContentPreview() {
    AppTheme {
        LoginFormContent(
            username = "Dusty Fox",
            password = "eleifend",
            onUsernameChanged = {},
            onPasswordChanged = {},
            onLoginButtonChanged = {},
            onRegisterButtonChanged = {})
    }
}
