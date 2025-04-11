package app.chris.aurawalls.screens.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.chris.aurawalls.model.videos.Video
import app.chris.aurawalls.repository.VideosRepository
import app.chris.aurawalls.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(private val videosRepository: VideosRepository) :
    ViewModel() {

    private val _videosStateFlow = MutableStateFlow<Resource<List<Video>>>(Resource.Idle())
    val videosStateFlow = _videosStateFlow.asStateFlow()

    init {
        fetchPopularVideos()
    }

    private fun fetchPopularVideos() {
        if (_videosStateFlow.value !is Resource.Success) {
            viewModelScope.launch {
                videosRepository.fetchPopularVideos()
                    .catch { videoException ->
                        _videosStateFlow.value =
                            Resource.Error("Error fetching videos:  ${videoException.localizedMessage}")
                    }
                    .collect { videoResponse ->
                        _videosStateFlow.value = when (videoResponse) {
                            is Resource.Loading -> Resource.Loading()

                            is Resource.Error -> Resource.Error(videoResponse.message)

                            is Resource.Idle -> Resource.Idle()

                            is Resource.Success -> Resource.Success(videoResponse.data.orEmpty())

                        }
                    }
            }
        }
    }
}