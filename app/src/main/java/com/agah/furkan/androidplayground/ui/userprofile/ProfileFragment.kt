package com.agah.furkan.androidplayground.ui.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.findNavController
import com.agah.furkan.androidplayground.MainGraphDirections
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.component.PlaceHolderImage
import com.agah.furkan.androidplayground.ui.theme.divider
import com.agah.furkan.androidplayground.ui.theme.gray
import com.agah.furkan.androidplayground.ui.theme.seed
import com.agah.furkan.androidplayground.util.SharedPrefUtil

class ProfileFragment : BaseFragment(null) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ProfileScreen {
                    findNavController().navigate(MainGraphDirections.actionGlobalLoginFragment())
                }
            }
        }
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }
}

@Composable
fun ProfileScreen(onLogoutButtonClicked: () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = seed)
        ) {
            val (notificationButton, logoutButton, userImgContainer, username) = createRefs()
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
                .padding(top = 16.dp, bottom = 51.dp), text = "Username")
            IconButton(
                modifier = Modifier.constrainAs(logoutButton) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
                onClick = {
                    SharedPrefUtil.clearAllData()
                    onLogoutButtonClicked()
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_logout),
                    tint = Color.White,
                    contentDescription = ""
                )
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
fun ProfileScreenPreview() {
    ProfileScreen()
}