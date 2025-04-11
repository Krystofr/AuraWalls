package app.chris.aurawalls.repository.favorite

import app.chris.aurawalls.room.favorites.FavoriteWallpaper
import app.chris.aurawalls.room.favorites.FavoriteWallpaperDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteWallpaperRepository @Inject constructor(private val favoriteWallpaperDao: FavoriteWallpaperDao) {

    fun getAllFavWallpapers(): Flow<List<FavoriteWallpaper>> {
        return favoriteWallpaperDao.getAllFavWallpapers()
    }

    suspend fun saveFavWallpaper(wallpaper: FavoriteWallpaper) {
        favoriteWallpaperDao.insertFavWallpaper(wallpaper)
    }

    suspend fun deleteFavWallpaper(wallpaper: FavoriteWallpaper) {
        favoriteWallpaperDao.deleteFavWallpaper(wallpaper)
    }

    suspend fun isFavorite(wallpaper: FavoriteWallpaper): Boolean {
        return favoriteWallpaperDao.getWallpaperById(wallpaper.id) != null
    }
}