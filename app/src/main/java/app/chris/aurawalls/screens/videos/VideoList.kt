package app.chris.aurawalls.screens.videos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.chris.aurawalls.model.videos.Video

@Composable
fun VideoList(videos: List<Video>) {

    /*LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        state = rememberLazyGridState(),
        modifier = Modifier.fillMaxSize()
    ) {
        items(videos) { video ->
            VideoItem(video = video)
        }
    }*/
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        state = rememberLazyStaggeredGridState(),
        modifier = Modifier.fillMaxSize()
    ) {
        items(videos) { video ->
            VideoItem(video = video)
        }
    }
}