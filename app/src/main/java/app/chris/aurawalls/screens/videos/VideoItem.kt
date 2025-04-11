package app.chris.aurawalls.screens.videos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.chris.aurawalls.model.videos.Video
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.RalewayMedium
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.Constants
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VideoItem(video: Video) {

    //val aspectRatio = 16f / 9f
    var isPlaying by remember { mutableStateOf(false) }

    Card(
        onClick = { isPlaying = !isPlaying },
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .wrapContentHeight()
            .padding(6.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(25.dp),
        backgroundColor = Background
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.8f),
            contentAlignment = Alignment.BottomStart
        ) {
            if (isPlaying) {
                ExoPlayer(
                    modifier = Modifier
                        .fillMaxSize(),
                    url = video.video_files.firstOrNull()?.link.orEmpty()
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(video.image),
                    contentDescription = "Thumbnail for ${video.user.name}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = Constants.shadowView,
                            startY = Float.POSITIVE_INFINITY,
                            endY = 0.5f
                        )
                    ),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                )
                Text(
                    text = "by ${video.user.name}",
                    color = White,
                    fontSize = 11.sp,
                    fontFamily = RalewayMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(bottom = 10.dp)
                )
            }

        }

    }
}