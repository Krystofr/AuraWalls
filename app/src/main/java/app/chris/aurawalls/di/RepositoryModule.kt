package app.chris.aurawalls.di

import app.chris.aurawalls.api.PexelsApi
import app.chris.aurawalls.repository.VideosRepository
import app.chris.aurawalls.repository.WallpaperRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWallpaperRepository(pexelsApi: PexelsApi): WallpaperRepository {
        return WallpaperRepository(pexelsApi)
    }

    @Provides
    @Singleton
    fun provideVideosRepository(pexelsApi: PexelsApi): VideosRepository
        = VideosRepository(pexelsApi)

}