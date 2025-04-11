package app.chris.aurawalls.screens.home.wallpaper_by_category

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.chris.aurawalls.screens.home.HomeScreenViewModel
import app.chris.aurawalls.screens.home.PhotoList
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.Purple
import app.chris.aurawalls.ui.theme.RalewayBold
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.LightItemsStatusLightNavBars
import app.chris.aurawalls.util.NavBar
import app.chris.aurawalls.wrapper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun WallpaperByCategory(navController: NavController, viewModel: HomeScreenViewModel) {
    val context = LocalContext.current
    val selectedCategoryState by viewModel.selectedCategoryStateFlow.collectAsStateWithLifecycle()
    val photosByCategoryState by viewModel.photosByCategory.collectAsStateWithLifecycle()
    val showProgressIndicator = remember {
        mutableStateOf(false)
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle
    var isStarted by remember { mutableStateOf(false) }

    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> isStarted = true

                Lifecycle.Event.ON_STOP -> isStarted = false

                Lifecycle.Event.ON_RESUME -> isStarted = false

                Lifecycle.Event.ON_PAUSE -> isStarted = false

                else -> Unit
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(isStarted, selectedCategoryState) {
        if (isStarted && photosByCategoryState !is Resource.Loading) {
            selectedCategoryState?.let {
                withContext(Dispatchers.Main) {
                    viewModel.fetchPhotosByCategory(it)
                }
            }
        }
    }

    LightItemsStatusLightNavBars(context = context)

    Surface(color = Background, modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                NavBar(icon = Icons.Default.ArrowBack) {
                    navController.popBackStack()
                }
                Text(
                    text = selectedCategoryState ?: "No category selected",
                    fontSize = 17.sp,
                    color = White,
                    fontFamily = RalewayBold,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 15.dp),
                    textAlign = TextAlign.Center
                )
            }
            if (showProgressIndicator.value) {
                CircularProgressIndicator(
                    strokeWidth = 5.dp,
                    strokeCap = StrokeCap.Round,
                    color = Purple,
                    modifier = Modifier.size(25.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            when (val state = photosByCategoryState) {
                is Resource.Success -> {
                    state.data?.let { photo ->
                        PhotoList(photos = photo, navController, viewModel)
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