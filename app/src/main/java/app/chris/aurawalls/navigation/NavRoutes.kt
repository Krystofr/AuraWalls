package app.chris.aurawalls.navigation

sealed class NavRoutes(val route: String) {
     data object LaunchScreen : NavRoutes(route = "launch_screen")

     data object HomeScreen : NavRoutes(route = "home_screen")
     data object FullPhotoScreen : NavRoutes(route = "full_photo_screen")
     data object VideosScreen : NavRoutes(route = "videos_screen")
     data object SearchScreen : NavRoutes(route = "search_screen")
     data object AllCategoriesScreen : NavRoutes(route = "all_categories_screen")
     data object WallpaperByCategory : NavRoutes(route = "wallpaper_by_category")

     data object AccountScreen : NavRoutes(route = "account_screen")
     data object ProfileUi : NavRoutes(route = "profile_ui")
     data object FavoritesUi : NavRoutes(route = "favorites_ui")
     data object SignInScreen : NavRoutes(route = "sign_in_screen")
     data object FullPhotoByFavScreen : NavRoutes(route = "full_photo_by_fav_screen")
}