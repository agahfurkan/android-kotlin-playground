package com.agah.furkan.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.agah.furkan.core.ui.theme.AppTheme
import com.agah.furkan.core.ui.theme.divider
import com.agah.furkan.core.ui.theme.gray
import com.agah.furkan.core.ui.theme.seed
import com.agah.furkan.ui.components.PlaceHolderImage
import com.agah.furkan.ui.components.WarningDialog

@Composable
internal fun ProfileRoute(onLogoutButtonClicked: () -> Unit) {
    ProfileScreen(onLogoutButtonClicked = onLogoutButtonClicked)
}

@Composable
private fun ProfileScreen(
    viewModel: ProfileScreenViewModel = hiltViewModel(),
    onLogoutButtonClicked: () -> Unit
) {
    val showLogoutDialog = remember {
        mutableStateOf(false)
    }

    WarningDialog(showLogoutDialog,
        title = "Warning",
        message = "Are you sure you want to logout ?",
        positiveButtonText = "Logout",
        negativeButtonText = "Cancel",
        onNegativeButtonClicked = {}) {
        viewModel.logout()
        onLogoutButtonClicked()
    }

    ProfileScreenContent(onLogoutButtonClicked = {
        showLogoutDialog.value = true
    }, onDownloadPdfClicked = {
        viewModel.downloadPdf()
    })
}

@Composable
internal fun ProfileScreenContent(onLogoutButtonClicked: () -> Unit, onDownloadPdfClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = seed)
        ) {
            val (notificationButton, buttonContainer, userImgContainer, username) = createRefs()
            Box(
                modifier = Modifier
                    .constrainAs(userImgContainer) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 24.dp)
                    .height(120.dp)
                    .width(120.dp)
                    .clip(CircleShape)
                    .background(color = gray)
            ) {
                PlaceHolderImage(
                    modifier = Modifier
                        .height(64.dp)
                        .width(64.dp)
                        .align(Alignment.Center)
                )
            }
            Text(modifier = Modifier
                .constrainAs(username) {
                    top.linkTo(userImgContainer.bottom)
                    start.linkTo(userImgContainer.start)
                    end.linkTo(userImgContainer.end)
                }
                .padding(top = 16.dp, bottom = 51.dp),
                text = stringResource(id = R.string.username))
            Column(modifier = Modifier.constrainAs(buttonContainer) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }) {
                IconButton(
                    onClick = {
                        onLogoutButtonClicked()
                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_logout),
                        tint = Color.White,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = {
                        onDownloadPdfClicked()
                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_cloud_download_24),
                        tint = Color.White,
                        contentDescription = ""
                    )
                }
            }
            IconButton(
                modifier = Modifier.constrainAs(notificationButton) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                },
                onClick = {
                    // TODO: navigate to notification list feature
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_bell_outline),
                    tint = Color.White,
                    contentDescription = ""
                )
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), content = {
            items(10) {
                Row(modifier = Modifier.clickable {
                    // TODO: add dynamic navigation
                }, verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                            .height(24.dp)
                            .width(24.dp),
                        painter = painterResource(id = R.drawable.placeholder_image),
                        contentDescription = ""
                    )
                    Text(modifier = Modifier.padding(start = 8.dp), text = "12345")
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        modifier = Modifier.padding(end = 16.dp),
                        painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_arrow_right)),
                        contentDescription = ""
                    )
                }
                Divider(modifier = Modifier.padding(horizontal = 16.dp), color = divider)
            }
        })
    }
}

@Composable
@Preview(showBackground = true)
private fun ProfileScreenPreview() {
    AppTheme {
        ProfileScreenContent(onLogoutButtonClicked = {}, onDownloadPdfClicked = {})

    }
}