package com.agah.furkan.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agah.furkan.ui.R

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    onFocusChanged: (focusState: FocusState) -> Unit,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(4.dp))
            .padding(8.dp)
    ) {
        SimpleTextField(
            modifier = modifier
                .fillMaxWidth()
                .height(24.dp)
                .onFocusChanged(onFocusChanged),
            value = "",
            onValueChange = onValueChange,
            placeholderText = stringResource(id = R.string.search),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "") }
        )
    }
}

@Preview
@Composable
fun SearchTextFieldPreview() {
    SearchTextField(onFocusChanged = {}, onValueChange = {})
}