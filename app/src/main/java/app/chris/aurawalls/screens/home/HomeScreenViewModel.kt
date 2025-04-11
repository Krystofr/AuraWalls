package app.chris.aurawalls.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.chris.aurawalls.model.curated_photos.Photo
import app.chris.aurawalls.repository.WallpaperRepository
import app.chris.aurawalls.room.favorites.FavoriteWallpaper
import app.chris.aurawalls.wrapper.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: WallpaperRepository) :
    ViewModel() {

    private val _photos = MutableStateFlow<Resource<List<Photo>>>(Resource.Idle())
    val photos = _photos.asStateFlow()

    private val _photosByCategory = MutableStateFlow<Resource<List<Photo>>>(Resource.Idle())
    val photosByCategory = _photosByCategory.asStateFlow()

    private val _photoMap = mutableMapOf<String, MutableStateFlow<Resource<Photo>?>>()

    private val _selectedPhoto = MutableStateFlow<Photo?>(null)
    val selectedPhoto = _selectedPhoto.asStateFlow()

    private val _selectedCategoryStateFlow = MutableStateFlow<String?>("")
    val selectedCategoryStateFlow = _selectedCategoryStateFlow.asStateFlow()

    /* init {

         categories.forEach { category ->
             _photoMap[category] = MutableStateFlow(null)
             fetchPhotoForCategory(category)
         }
     }*/

    init {
        loadCuratedPhotos()
    }

    private fun loadCuratedPhotos() {
        if (_photos.value !is Resource.Success) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getCuratedPhotos().collect { photosResult ->
                    _photos.value = photosResult
                }
            }
        }
    }

    fun fetchPhotosByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchPhotos(category).collect { resource ->
                _photosByCategory.value = resource
            }
        }
    }

    fun saveSelectedCategory(category: String) {
        _selectedCategoryStateFlow.value = category
    }

    fun selectPhoto(photo: Photo) {
        _selectedPhoto.value = photo
    }

}