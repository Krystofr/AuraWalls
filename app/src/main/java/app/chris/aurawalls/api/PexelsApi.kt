package app.chris.aurawalls.api

import app.chris.aurawalls.BuildConfig
import app.chris.aurawalls.model.curated_photos.PhotoResponse
import app.chris.aurawalls.model.videos.PopularVideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsApi {

    @Headers("Authorization: ${BuildConfig.PEXELS_API_KEY}")
    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<PhotoResponse>

    @Headers("Authorization: ${BuildConfig.PEXELS_API_KEY}")
    @GET("videos/popular")
    suspend fun getPopularVideos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<PopularVideosResponse>

    @Headers("Authorization: ${BuildConfig.PEXELS_API_KEY}")
    @GET("search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<PhotoResponse>
}