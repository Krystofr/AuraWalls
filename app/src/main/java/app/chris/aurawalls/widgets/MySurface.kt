package app.chris.aurawalls.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.chris.aurawalls.room.favorites.FavoriteWallpaper
import app.chris.aurawalls.screens.account.ProfileViewModel
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.IconBackground
import app.chris.aurawalls.ui.theme.White

@Composable
fun MySurface(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
fun FavoriteIcon(favoriteWallpaper: FavoriteWallpaper) {

    val profileViewModel: ProfileViewModel = hiltViewModel()
    var isFavorite by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(favoriteWallpaper) {
        val isFav = profileViewModel.isFavorite(favoriteWallpaper)
        isFavorite = isFav
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(
            onClick = {
                isFavorite = !isFavorite
                if (isFavorite) {
                    profileViewModel.saveFavWallpaper(favoriteWallpaper)
                } else {
                    profileViewModel.deleteWallpaper(favoriteWallpaper)
                }
            },
            modifier = Modifier
                .size(30.dp)
                .background(color = IconBackground, shape = CircleShape)
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "FavoriteWallpaper icon",
                modifier = Modifier.size(30.dp),
                tint = if (isFavorite) Color.Red else White
            )
        }
    }
}