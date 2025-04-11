package app.chris.aurawalls.screens.search

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
import app.chris.aurawalls.screens.home.HomeScreenViewModel

@Composable
fun SearchList(photos: List<Photo>, navController: NavController, viewModel: HomeScreenViewModel) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        state = rememberLazyStaggeredGridState(),
        modifier = Modifier.fillMaxSize()
    ) {
        items(photos) { photo ->
            SearchItem(photo = photo, viewModel, navController)
        }
    }
}