package app.chris.aurawalls.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.model.curated_photos.Photo
import app.chris.aurawalls.navigation.NavRoutes
import app.chris.aurawalls.room.favorites.FavoriteWallpaper
import app.chris.aurawalls.screens.home.HomeScreenViewModel
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.RalewayMedium
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.Constants
import app.chris.aurawalls.widgets.FavoriteIcon
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchItem(
    photo: Photo,
    viewModel: HomeScreenViewModel,
    navController: NavController
) {

    val photoAlt = rememberUpdatedState(newValue = photo)
    val favoriteWallpaper = FavoriteWallpaper(
        id = photo.id,
        url = photo.src.portrait,
        photographer = photo.photographer,
        alt = photo.alt,
        src = photo.src,
        photographer_url = photo.photographer_url
    )


    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(6.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(25.dp),
        onClick = {
            viewModel.selectPhoto(photo)
            Log.d("SelectedPhotoPhotographer: ", photo.photographer)
            navController.navigate(NavRoutes.FullPhotoScreen.route)
        },
        backgroundColor = Background
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = photo.src.original,
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.loading_error),
                    placeholder = painterResource(id = R.drawable.loading_icon),
                    filterQuality = FilterQuality.High
                ),
                contentDescription = photoAlt.value.alt ?: "Photo by ${photo.photographer}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Crop
            )
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
                    text = "by ${photo.photographer}",
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
        FavoriteIcon(favoriteWallpaper)
    }
}