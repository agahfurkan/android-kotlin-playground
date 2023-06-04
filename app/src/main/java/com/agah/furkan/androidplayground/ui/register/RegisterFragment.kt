package com.agah.furkan.androidplayground.ui.register

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.util.launchAndCollectIn

@Composable
fun RegisterScreen(viewModel: RegisterFragmentVM = hiltViewModel(), onRegisterSuccess: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        viewModel.registerUserResponse.launchAndCollectIn(lifecycleOwner) { state ->
            when (state) {
                is Result.Success -> {
                    onRegisterSuccess
                }

                is Result.Failure -> {
                    //  showLongToast(apiResponse.error.errorMessage)
                }
            }
        }
    }

    RegisterFormContent(
        username = viewModel.username,
        password = viewModel.password,
        onUsernameChanged = {
            viewModel.username = it
        },
        onPasswordChanged = {
            viewModel.password = it
        }, onRegisterClick = viewModel::registerNewUser
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RegisterFormContent(
    username: String,
    password: String,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    AppTheme {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 36.dp)
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
                    onRegisterClick()
                }) {
                    Text(stringResource(id = R.string.register))
                }
            }
        }
    }
}

@Preview
@Composable
fun RegisterFormContentPreview() {
    RegisterFormContent(
        username = "Dion Grant",
        password = "ocurreret",
        onUsernameChanged = {},
        onPasswordChanged = {},
        onRegisterClick = {})
}