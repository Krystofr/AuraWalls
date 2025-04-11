package app.chris.aurawalls.screens.home

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
import app.chris.aurawalls.model.curated_photos.Photo

@Composable
fun PhotoList(photos: List<Photo>, navController: NavController, viewModel: HomeScreenViewModel) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 1.dp),
        state = rememberLazyStaggeredGridState(),
        modifier = Modifier.fillMaxSize()
    ) {
        items(photos) { photo ->
            PhotoItem(photo = photo, navController, viewModel)
        }
    }
}