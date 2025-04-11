package app.chris.aurawalls.model.videos

data class VideoFile(
    val id: Int,
    val quality: String,
    val file_type: String,
    val width: Int,
    val height: Int,
    val link: String
)
