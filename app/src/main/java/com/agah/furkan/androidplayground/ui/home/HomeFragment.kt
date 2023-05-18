package com.agah.furkan.androidplayground.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agah.furkan.androidplayground.ui.base.BaseFragment
import com.agah.furkan.androidplayground.ui.component.ImageBox
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.ui.theme.seed

class HomeFragment : BaseFragment(null) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreen()
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen() {
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = seed)
                )
            }
        ) { padding ->
            LazyColumn(modifier = Modifier
                .padding(padding)
                .padding(16.dp), content = {
                item {
                    Text(text = "Announcements")
                    LazyRow(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(100) {
                            ImageBox(
                                Modifier
                                    .height(80.dp)
                                    .width(128.dp)
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Exclusive Deals")
                    LazyRow(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(100) {
                            Column(
                                modifier = Modifier
                                    .width(96.dp)
                            ) {
                                ImageBox(
                                    Modifier
                                        .height(96.dp)
                                        .width(96.dp)
                                )
                                Spacer(Modifier.height(4.dp))
                                Text("$100", fontSize = 10.sp, maxLines = 1)
                                Text(
                                    "$100",
                                    fontSize = 10.sp,
                                    maxLines = 1,
                                    textDecoration = TextDecoration.LineThrough
                                )
                                Text(
                                    "Product Name",
                                    fontSize = 10.sp,
                                    maxLines = 3,
                                    lineHeight = 16.sp
                                )
                            }
                        }
                    }
                }
                item {
                    Spacer(Modifier.height(16.dp))
                    Text("Recently Viewed")
                    LazyRow(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(100) {
                            Column(
                                modifier = Modifier
                                    .width(158.dp)
                            ) {
                                ImageBox(
                                    Modifier
                                        .height(84.dp)
                                        .width(158.dp)
                                )
                                Spacer(Modifier.height(4.dp))
                                Text("Product Name", fontSize = 10.sp, maxLines = 1)
                            }
                        }
                    }
                }
                item {
                    Spacer(Modifier.height(16.dp))
                }
                items(100) {
                    ImageBox(
                        Modifier
                            .fillMaxWidth()
                            .height(101.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                }
            })
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}