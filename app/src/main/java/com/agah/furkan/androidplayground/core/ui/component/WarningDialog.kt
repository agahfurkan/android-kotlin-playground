package com.agah.furkan.androidplayground.core.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.agah.furkan.androidplayground.R
import com.agah.furkan.androidplayground.ui.theme.seed

@Composable
fun WarningDialog(
    showDialog: MutableState<Boolean>,
    title: String = "Warning",
    message: String = "Logout ?",
    actionButtonText: String = "Logout",
    dismissButtonText: String = "Cancel",
    onActionButtonClicked: () -> Unit,
) {
    Dialog(onDismissRequest = { showDialog.value = false }) {
        CustomDialogContent(
            openDialogCustom = showDialog,
            title = title,
            message = message,
            actionButtonText = actionButtonText,
            dismissButtonText = dismissButtonText,
            onActionButtonClicked = onActionButtonClicked
        )
    }
}

//Layout
@Composable
fun CustomDialogContent(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    title: String = "Warning",
    message: String = "Logout ?",
    actionButtonText: String = "Logout",
    dismissButtonText: String = "Cancel",
    onActionButtonClicked: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bell_outline),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = seed
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = message,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            //.......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(seed),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(onClick = {
                    openDialogCustom.value = false
                }) {
                    Text(
                        dismissButtonText,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                TextButton(onClick = {
                    openDialogCustom.value = false
                    onActionButtonClicked()
                }) {
                    Text(
                        actionButtonText,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyDialogUIPreview() {
    CustomDialogContent(openDialogCustom = mutableStateOf(false), onActionButtonClicked = {})
}