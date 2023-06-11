package com.agah.furkan.androidplayground.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.core.ui.component.PlaceHolderImage
import com.agah.furkan.androidplayground.core.ui.component.SearchTextField
import com.agah.furkan.androidplayground.ui.theme.AppTheme
import com.agah.furkan.androidplayground.ui.theme.seed

@Composable
fun HomeScreen(onSearchFocused: () -> Unit) {
    val focusManager = LocalFocusManager.current
    AppTheme {
        Surface {
            Column {
                SearchContent {
                    if(it.hasFocus){
                        focusManager.clearFocus()
                        onSearchFocused()
                    }
                }
                LazyColumn(modifier = Modifier.padding(16.dp), content = {
                    item {
                        Text(text = stringResource(R.string.announcements))
                        LazyRow(
                            modifier = Modifier.padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(100) {
                                PlaceHolderImage(
                                    Modifier
                                        .height(80.dp)
                                        .width(128.dp)
                                )
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(stringResource(id = R.string.exclusive_deals))
                        LazyRow(
                            modifier = Modifier.padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(100) {
                                Column(
                                    modifier = Modifier
                                        .width(96.dp)
                                ) {
                                    PlaceHolderImage(
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
                        Text(stringResource(id = R.string.recently_viewed))
                        LazyRow(
                            modifier = Modifier.padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(100) {
                                Column(
                                    modifier = Modifier
                                        .width(158.dp)
                                ) {
                                    PlaceHolderImage(
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
                        PlaceHolderImage(
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
}

@Composable
fun SearchContent(onSearchFocused: (focusState: FocusState) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .background(seed)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        SearchTextField(onFocusChanged = {
            onSearchFocused(it)
        }, onValueChange = {})
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen {}
}