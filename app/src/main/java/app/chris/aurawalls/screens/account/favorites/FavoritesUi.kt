package app.chris.aurawalls.screens.account.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.chris.aurawalls.screens.account.ProfileViewModel
import app.chris.aurawalls.ui.theme.LightGray
import app.chris.aurawalls.ui.theme.MontserratAlternatesMedium
import app.chris.aurawalls.ui.theme.RalewayMedium
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.LightItemsStatusLightNavBars
import app.chris.aurawalls.util.NavBar
import app.chris.aurawalls.widgets.MySurface

@Composable
fun FavoritesUi(navController: NavController) {

    val profileViewModel: ProfileViewModel = hiltViewModel()
    val favWallpapersListState by profileViewModel.favoriteWallpapers.collectAsStateWithLifecycle()

    val context = LocalContext.current
    LightItemsStatusLightNavBars(context)
    MySurface(content = {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            NavBar(icon = Icons.Filled.ArrowBack) { navController.popBackStack() }
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Favorites", fontSize = 18.sp, fontFamily = RalewayMedium, color = White)
        }
        Spacer(modifier = Modifier.height(15.dp))

        if (favWallpapersListState.isEmpty()) {
            Text(
                text = "You have no wallpapers saved",
                color = LightGray,
                fontSize = 12.sp,
                fontFamily = MontserratAlternatesMedium,
                modifier = Modifier.fillMaxWidth().padding(top = 100.dp),
                textAlign = TextAlign.Center
            )
        }
        FavList(
            favItems = favWallpapersListState,
            navController = navController,
            viewModel = profileViewModel
        )
    })

}