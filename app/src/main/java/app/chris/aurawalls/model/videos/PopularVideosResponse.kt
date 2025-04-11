package app.chris.aurawalls.model.videos

data class PopularVideosResponse(
    val page: Int,
    val per_page: Int,
    val videos: List<Video>
)
