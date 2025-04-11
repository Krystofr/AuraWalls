package app.chris.aurawalls.repository

import app.chris.aurawalls.api.PexelsApi
import app.chris.aurawalls.model.videos.Video
import app.chris.aurawalls.wrapper.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideosRepository @Inject constructor(private val pexelsApi: PexelsApi) {

    suspend fun fetchPopularVideos(): Flow<Resource<List<Video>>> =
        flow{
            emit(Resource.Loading())

            try {
                val response = pexelsApi.getPopularVideos(page = 1, perPage = 16)
                if (response.isSuccessful && response.body() != null) {
                    emit(Resource.Success(response.body()!!.videos))
                } else {
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An error occurred fetching popular videos"))
            }

        }
}