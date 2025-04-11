package app.chris.aurawalls.room.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.chris.aurawalls.model.curated_photos.PhotoSrc

@Entity(tableName = "favorite_wallpapers")
data class FavoriteWallpaper(
    @PrimaryKey val id: Int = 0,
    val url: String,
    val photographer: String,
    val photographer_url: String,
    val alt: String?,
    val src: PhotoSrc
)
