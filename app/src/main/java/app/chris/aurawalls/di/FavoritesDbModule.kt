package app.chris.aurawalls.di

import android.content.Context
import androidx.room.Room
import app.chris.aurawalls.room.favorites.FavoriteWallpaperDao
import app.chris.aurawalls.room.favorites.FavoritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesDbModule {

    @Provides
    @Singleton
    fun provideFavoritesDatabase(@ApplicationContext appContext: Context): FavoritesDatabase =
        Room.databaseBuilder(
            appContext,
            FavoritesDatabase::class.java,
            "favorites.db"
        ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideFavoritesDao(database: FavoritesDatabase): FavoriteWallpaperDao =
        database.favoritesDao()

}