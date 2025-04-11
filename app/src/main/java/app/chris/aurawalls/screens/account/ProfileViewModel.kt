package app.chris.aurawalls.screens.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.chris.aurawalls.repository.favorite.FavoriteWallpaperRepository
import app.chris.aurawalls.repository.profile.ProfileRepository
import app.chris.aurawalls.room.EmailTable
import app.chris.aurawalls.room.Profile
import app.chris.aurawalls.room.favorites.FavoriteWallpaper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val favWallpaperRepository: FavoriteWallpaperRepository
) :
    ViewModel() {

    private val _profileStateFlow = MutableStateFlow<Profile?>(null)
    val profileStateFlow = _profileStateFlow.asStateFlow()

    private val _favoriteWallpapers = MutableStateFlow<List<FavoriteWallpaper>>(emptyList())
    val favoriteWallpapers = _favoriteWallpapers.asStateFlow()

    private val _selectedFavWallpaper = MutableStateFlow<FavoriteWallpaper?>(null)
    val selectedFavWallpaper = _selectedFavWallpaper.asStateFlow()

    private val _emailStateFlow = MutableStateFlow<EmailTable?>(null)
    val emailStateFlow = _emailStateFlow.asStateFlow()

    init {
        loadProfile(0)
        getEmail()
        loadAllFavWallpapers()
    }

    private fun loadProfile(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.getProfile(id).collect { profile ->
                _profileStateFlow.value = profile
            }
        }
    }
    private fun getEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.getEmail(0).collect { emailId ->
                _emailStateFlow.value = emailId
            }
        }
    }

    fun saveProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.saveProfile(profile)
            loadProfile(profile.id)
        }
    }
    fun saveEmail(email: EmailTable) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.insertEmail(email)
        }
    }

    fun deleteProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            profileRepository.deleteProfile(profile)
            _profileStateFlow.value = null
        }
    }


    private fun loadAllFavWallpapers() {
        viewModelScope.launch {
            favWallpaperRepository.getAllFavWallpapers().collect { wallpaperList ->
                _favoriteWallpapers.value = wallpaperList
            }
        }
    }

    fun saveFavWallpaper(wallpaper: FavoriteWallpaper) {
        viewModelScope.launch {
            favWallpaperRepository.saveFavWallpaper(wallpaper)
            loadAllFavWallpapers()
        }
    }

    fun deleteWallpaper(wallpaper: FavoriteWallpaper) {
        viewModelScope.launch {
            favWallpaperRepository.deleteFavWallpaper(wallpaper)
            loadAllFavWallpapers()
        }
    }

    suspend fun isFavorite(wallpaper: FavoriteWallpaper): Boolean =
        favWallpaperRepository.isFavorite(wallpaper)

    fun selectFavPhoto(favWallpaper: FavoriteWallpaper) {
        _selectedFavWallpaper.value = favWallpaper
    }
}