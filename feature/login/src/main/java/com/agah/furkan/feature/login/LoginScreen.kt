package com.agah.furkan.feature.login

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.core.util.ext.launchAndCollectIn
import com.agah.furkan.core.util.ext.showToast
import com.agah.furkan.domain.login.LoginUseCase
import com.agah.furkan.ui.components.OTPDialog

@Composable
internal fun LoginRoute(
    onLoginSuccess: () -> Unit,
    onRegisterClicked: () -> Unit,
    viewModel: LoginScreenVM = hiltViewModel(),
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

                LoginUseCase.UiState.Loading -> {}
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
    LoginScreen(
        username = viewModel.username,
        password = viewModel.password,
        onLoginSuccess = viewModel::onLoginBtnClicked,
        onRegisterClicked = viewModel::onRegisterButtonClicked,
        onUsernameChanged = { viewModel.username = it },
        onPasswordChanged = { viewModel.password = it },
    )
}

@Composable
internal fun LoginScreen(
    username: String,
    password: String,
    onLoginSuccess: () -> Unit,
    onRegisterClicked: () -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
) {
    LoginFormContent(
        username = username,
        password = password,
        onUsernameChanged = onUsernameChanged,
        onPasswordChanged = onPasswordChanged,
        onLoginButtonClicked = onLoginSuccess,
        onRegisterButtonClicked = onRegisterClicked
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LoginFormContent(
    modifier: Modifier = Modifier,
    username: String,
    password: String,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit,
    onRegisterButtonClicked: () -> Unit
) {
    val userNameHint = stringResource(id = R.string.username)
    val passwordHint = stringResource(id = R.string.password)
    val showOTPDialog = remember { mutableStateOf(false) }
    if (showOTPDialog.value) {
        OTPDialog(
            showDialog = showOTPDialog,
            onDismiss = {
                showOTPDialog.value = false
            }
        )
    }

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
        OutlinedTextField(
            value = username,
            label = { Text(text = userNameHint) },
            modifier = Modifier
                .semantics {
                    contentDescription = userNameHint
                }
                .fillMaxWidth()
                .padding(top = 24.dp),
            onValueChange = {
                onUsernameChanged(it)
            }
        )
        OutlinedTextField(
            value = password,
            label = { Text(text = passwordHint) },
            modifier = Modifier
                .semantics {
                    contentDescription = passwordHint
                }
                .fillMaxWidth()
                .padding(top = 12.dp),
            onValueChange = {
                onPasswordChanged(it)
            }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            onClick = {
                onLoginButtonClicked()
            }
        ) {
            Text(stringResource(id = R.string.login))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            onClick = {
                onRegisterButtonClicked()
            }
        ) {
            Text(stringResource(id = R.string.register))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            onClick = {
                showOTPDialog.value = true
            }
        ) {
            Text(stringResource(id = R.string.show_otp))
        }
    }
}

@Composable
@Preview
private fun LoginFormContentPreview() {
    AppTheme {
        Surface {
            LoginFormContent(
                username = "Dusty Fox",
                password = "eleifend",
                onUsernameChanged = {},
                onPasswordChanged = {},
                onLoginButtonClicked = {}
            ) {}
        }
    }
}
