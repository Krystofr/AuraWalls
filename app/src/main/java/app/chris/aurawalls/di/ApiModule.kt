package app.chris.aurawalls.di

import app.chris.aurawalls.api.PexelsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePexelsApi(retrofit: Retrofit): PexelsApi = retrofit.create(PexelsApi::class.java)
}