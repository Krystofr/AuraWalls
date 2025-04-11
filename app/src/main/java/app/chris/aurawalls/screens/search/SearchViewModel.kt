package app.chris.aurawalls.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.chris.aurawalls.model.curated_photos.Photo
import app.chris.aurawalls.repository.WallpaperRepository
import app.chris.aurawalls.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val wallpaperRepository: WallpaperRepository) :
    ViewModel() {

    private val _searchQueryStateFlow = MutableStateFlow("")
    val searchQueryStateFlow = _searchQueryStateFlow.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val suggestionsStateflow: StateFlow<Resource<List<Photo>>> =
        _searchQueryStateFlow.flatMapLatest { query ->
            if (query.isEmpty()) {
                flow {
                    emit(Resource.Success(data = emptyList()))
                }
            } else {
                wallpaperRepository.searchPhotos(query)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Resource.Success(data = emptyList())
        )

    fun setSearchQuery(query: String) {
        _searchQueryStateFlow.value = query
    }

}