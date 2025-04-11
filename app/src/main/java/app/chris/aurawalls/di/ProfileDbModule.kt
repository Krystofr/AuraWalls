package app.chris.aurawalls.di

import android.content.Context
import androidx.room.Room
import app.chris.aurawalls.room.ProfileDao
import app.chris.aurawalls.room.ProfileDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileDbModule {

    @Provides
    @Singleton
    fun provideProfileDatabase(@ApplicationContext appContext: Context): ProfileDatabase =
        Room.databaseBuilder(
            appContext,
            ProfileDatabase::class.java,
            "account.db"
        ).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideProfileDao(database: ProfileDatabase): ProfileDao = database.profileDao()

}