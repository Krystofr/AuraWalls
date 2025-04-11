package app.chris.aurawalls.room.favorites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteWallpaperDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavWallpaper(wallpaper: FavoriteWallpaper)

    @Delete
    suspend fun deleteFavWallpaper(wallpaper: FavoriteWallpaper)
    @Query("SELECT * FROM favorite_wallpapers WHERE id = :id LIMIT 1")
    suspend fun getWallpaperById(id: Int): FavoriteWallpaper?

    @Query("SELECT * FROM favorite_wallpapers")
    fun getAllFavWallpapers(): Flow<List<FavoriteWallpaper>>

}