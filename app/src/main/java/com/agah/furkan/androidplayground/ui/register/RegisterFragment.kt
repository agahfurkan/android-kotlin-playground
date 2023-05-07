package com.agah.furkan.androidplayground.ui.register

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
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.databinding.FragmentRegisterBinding
import com.agah.furkan.androidplayground.domain.Result
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.util.showLongToast
import com.agah.furkan.androidplayground.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {
    override val toolbarType: ToolbarType
        get() = ToolbarType.WithActionButtons(listOf(ToolbarType.ToolbarButton.BACK))
    private val binding by viewBinding(FragmentRegisterBinding::bind)
    private val viewModel by viewModels<RegisterFragmentVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            AppTheme {
                RegisterScreen()
            }
        }
        initObservers()
    }

    private fun initObservers() {
        viewModel.registerUserResponse.observe(viewLifecycleOwner) { apiResponse ->
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    fun RegisterScreen() {
        val username = viewModel.username
        val password = viewModel.password

        Surface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 36.dp),
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
                    viewModel.registerNewUser()
                }) {
                    Text(stringResource(id = R.string.register))
                }
            }
        }
    }
}
