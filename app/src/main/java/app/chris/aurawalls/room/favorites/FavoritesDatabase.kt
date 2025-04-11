package app.chris.aurawalls.room.favorites

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavoriteWallpaper::class], version = 3)
@TypeConverters(PhotoSrcConverter::class)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoriteWallpaperDao
}