package app.chris.aurawalls.model.curated_photos

data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val photographer: String,
    val photographer_url: String,
    val alt: String?,
    val src: PhotoSrc,
    val url: String
    //var height: Int = calculateHeightForImage()
)
fun calculateHeightForImage(): Int {
    // Placeholder function to calculate height based on image's aspect ratio
    // This function would normally calculate based on the actual image's aspect ratio
    // For demonstration, return a fixed value or randomize
    return (250..450).last// Random heights for demonstration purposes
}
