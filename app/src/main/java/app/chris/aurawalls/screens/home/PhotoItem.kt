package app.chris.aurawalls.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.model.curated_photos.Photo
import app.chris.aurawalls.navigation.NavRoutes
import app.chris.aurawalls.room.favorites.FavoriteWallpaper
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.widgets.FavoriteIcon
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhotoItem(photo: Photo, navController: NavController, viewModel: HomeScreenViewModel) {

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
            .fillMaxWidth(0.95f)
            .wrapContentHeight()
            .padding(6.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        onClick = {
            viewModel.selectPhoto(photo)
            Log.d("SelectedPhotoPhotographer: ", photo.photographer)
            navController.navigate(NavRoutes.FullPhotoScreen.route)
        },
        backgroundColor = Background
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .wrapContentHeight(),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = photo.src.portrait,
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
            FavoriteIcon(favoriteWallpaper)
        }

    }
}