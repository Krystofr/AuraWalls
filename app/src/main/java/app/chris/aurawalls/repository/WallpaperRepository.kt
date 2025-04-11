package app.chris.aurawalls.repository

import app.chris.aurawalls.api.PexelsApi
import app.chris.aurawalls.model.curated_photos.Photo
import app.chris.aurawalls.wrapper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WallpaperRepository @Inject constructor(private val pexelsApi: PexelsApi) {

    suspend fun getCuratedPhotos(): Flow<Resource<List<Photo>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = pexelsApi.getCuratedPhotos(1, 55)

                if (response.isSuccessful && response.body() != null) {
                    emit(Resource.Success(response.body()!!.photos))
                } else {
                    emit(Resource.Error(response.errorBody().toString()))
                }

            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
            }
        }

    suspend fun searchPhotos(query: String): Flow<Resource<List<Photo>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = pexelsApi.searchPhotos(query, 1, 25)

                if (response.isSuccessful && response.body() != null) {
                    emit(Resource.Success(response.body()!!.photos))
                } else {
                    emit(Resource.Error(response.errorBody().toString()))
                }

            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An error occurred"))
            }
        }
}