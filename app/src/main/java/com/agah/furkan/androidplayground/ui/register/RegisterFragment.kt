package com.agah.furkan.androidplayground.ui.register

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.util.launchAndCollectIn
import com.agah.furkan.androidplayground.util.showLongToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment(null) {
    override val toolbarType: ToolbarType
        get() = ToolbarType.WithActionButtons(listOf(ToolbarType.ToolbarButton.BACK))
    private val viewModel by viewModels<RegisterFragmentVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initObservers()
        return ComposeView(requireContext()).apply {
            setContent {
                RegisterScreen()
            }
        }
    }

    private fun initObservers() {
        viewModel.registerUserResponse.launchAndCollectIn(viewLifecycleOwner) { apiResponse ->
            when (apiResponse) {
                is Result.Success -> {
                    showLongToast(apiResponse.data)
                    navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                }

                is Result.Failure -> {
                    showLongToast(apiResponse.error.errorMessage)
                }
            }
        }
    }
}


@Composable
fun RegisterScreen() {
    val viewModel = hiltViewModel<RegisterFragmentVM>()
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