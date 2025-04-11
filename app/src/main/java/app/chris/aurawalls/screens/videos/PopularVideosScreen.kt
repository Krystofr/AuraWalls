package app.chris.aurawalls.screens.videos

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.chris.aurawalls.R
import app.chris.aurawalls.ui.theme.Purple
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.LightItemsStatusLightNavBars
import app.chris.aurawalls.widgets.BackgroundOverlay
import app.chris.aurawalls.wrapper.Resource

@Composable
fun PopularVideosScreen(viewModel: VideosViewModel) {

    val context = LocalContext.current
    val videosState by viewModel.videosStateFlow.collectAsStateWithLifecycle()
    LightItemsStatusLightNavBars(context = context)
    val showProgressIndicator = remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        BackgroundOverlay()
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    top = 20.dp, bottom =
                    60.dp
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = stringResource(R.string.popular_videos),
                fontSize = 20.sp,
                color = White,
                fontFamily = RalewayBold,
                modifier = Modifier
                    .wrapContentSize()
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (showProgressIndicator.value) {
                CircularProgressIndicator(
                    strokeWidth = 5.dp,
                    strokeCap = StrokeCap.Round,
                    color = Purple,
                    modifier = Modifier.size(25.dp)
                )
            }
            when (val state = videosState) {
                is Resource.Success -> {
                    state.data?.let {
                        VideoList(videos = it)
                    }
                    showProgressIndicator.value = false
                }

                is Resource.Error -> {
                    Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                    showProgressIndicator.value = false
                }

                is Resource.Loading -> {
                    showProgressIndicator.value = true
                }

                is Resource.Idle -> {

                }
            }

        }
    }
}