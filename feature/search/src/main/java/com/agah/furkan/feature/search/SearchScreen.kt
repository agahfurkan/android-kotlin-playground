package com.agah.furkan.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agah.furkan.core.ui.component.SearchTextField
import com.agah.furkan.core.ui.theme.seed
import com.agah.furkan.search.R

@Composable
fun SearchRoute(onBackPress: () -> Unit) {
    SearchScreen(onBackPress = onBackPress)
}

@Composable
private fun SearchScreen(onBackPress: () -> Unit) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), content = {
        item {
            Column(
                modifier = Modifier
                    .graphicsLayer {
                        scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                        translationY = scrolledY * 0.5f
                        previousOffset = lazyListState.firstVisibleItemScrollOffset
                    }
                    .fillMaxWidth()
                    .background(seed)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_left_arrow_outline),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                    SearchTextField(
                        modifier = Modifier.focusRequester(focusRequester),
                        onFocusChanged = {},
                        onValueChange = {})
                }

            }
        }

        items(100) {
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .fillMaxWidth()
                    .background(Color.Black)
            )
        }
    })
}

@Composable
@Preview
private fun SearchScreenPreview() {
    SearchScreen {

    }
}