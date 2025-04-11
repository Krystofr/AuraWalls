package app.chris.aurawalls.model.videos

data class Video(
    val id: Int,
    val url: String,
    val image: String,
    val duration: Int,
    val user: User,
    val video_files: List<VideoFile>
)
