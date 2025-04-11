package app.chris.aurawalls.screens.account.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.chris.aurawalls.room.favorites.FavoriteWallpaper
import app.chris.aurawalls.screens.account.ProfileViewModel

@Composable
fun FavList(
    favItems: List<FavoriteWallpaper>,
    navController: NavController,
    viewModel: ProfileViewModel
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 1.dp),
        state = rememberLazyStaggeredGridState(),
        modifier = Modifier.fillMaxSize()
    ) {
        items(favItems) { favItems ->
            FavItem(favorite = favItems, navController, viewModel)
        }
    }
}