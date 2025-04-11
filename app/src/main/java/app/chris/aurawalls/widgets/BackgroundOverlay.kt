package app.chris.aurawalls.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import app.chris.aurawalls.R
import app.chris.aurawalls.ui.theme.DarkBackgroundLight

@Composable
fun BackgroundOverlay() {
    Image(
        painter = painterResource(id = R.drawable.wall2),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Box(modifier = Modifier.fillMaxSize().background(color = DarkBackgroundLight))
}