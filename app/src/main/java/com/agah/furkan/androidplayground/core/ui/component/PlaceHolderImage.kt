package com.agah.furkan.androidplayground.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.agah.furkan.androidplayground.R

@Composable
fun PlaceHolderImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color(0xFFE3E3E3)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(id = R.drawable.placeholder_image),
            "",
        )
    }
}

@Composable
@Preview
fun ImageBoxPreview() {
    PlaceHolderImage(modifier = Modifier
        .height(100.dp)
        .width(100.dp))
}