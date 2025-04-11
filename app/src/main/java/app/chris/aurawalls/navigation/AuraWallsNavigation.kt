package app.chris.aurawalls.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.chris.aurawalls.screens.FullPhotoScreen
import app.chris.aurawalls.screens.LaunchScreen
import app.chris.aurawalls.screens.account.AccountScreen
import app.chris.aurawalls.screens.account.ProfileUi
import app.chris.aurawalls.screens.account.ProfileViewModel
import app.chris.aurawalls.screens.account.favorites.FavoritesUi
import app.chris.aurawalls.screens.account.favorites.FullPhotoByFavScreen
import app.chris.aurawalls.screens.auth.SignInScreen
import app.chris.aurawalls.screens.home.HomeScreen
import app.chris.aurawalls.screens.home.HomeScreenViewModel
import app.chris.aurawalls.screens.home.all_categories.AllCategoriesScreen
import app.chris.aurawalls.screens.home.wallpaper_by_category.WallpaperByCategory
import app.chris.aurawalls.screens.search.SearchScreen
import app.chris.aurawalls.screens.search.SearchViewModel
import app.chris.aurawalls.screens.videos.PopularVideosScreen
import app.chris.aurawalls.screens.videos.VideosViewModel

@Composable
fun AuraWallsNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
    val videosViewModel = hiltViewModel<VideosViewModel>()
    val searchViewModel = hiltViewModel<SearchViewModel>()
    val profileViewModel = hiltViewModel<ProfileViewModel>()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LaunchScreen.route
        /*enterTransition = {
            customEnterTransition()
        },
        exitTransition = {
            customExitTransition()
        },
        popEnterTransition = {
            customPopEnterTransition()
        },
        popExitTransition = {
            customPopExitTransition()
        }*/,
        builder = {
            composable(
                route = NavRoutes.HomeScreen.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                    /*slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )*/
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                    /*slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )*/
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                    /*slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )*/
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                    /*slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )*/
                }
            ) {
                HomeScreen(navController, homeScreenViewModel)
            }
            composable(
                route = NavRoutes.FullPhotoScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                }
            ) {
                FullPhotoScreen(homeScreenViewModel, navController)
            }

            composable(
                route = NavRoutes.VideosScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                }
            ) {
                PopularVideosScreen(videosViewModel)
            }

            composable(
                route = NavRoutes.SearchScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                }
            ) {
                SearchScreen(navController, searchViewModel, homeScreenViewModel)
            }

            composable(
                route = NavRoutes.LaunchScreen.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                    /*slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )*/
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                    /*slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )*/
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                    /*slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )*/
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                    /*slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )*/
                }
            ) {
                LaunchScreen(navController)
            }

            composable(
                route = NavRoutes.AllCategoriesScreen.route,
                enterTransition = {
                    //fadeIn(animationSpec = tween(durationMillis = 300))
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = {
                    //fadeOut(animationSpec = tween(durationMillis = 300))
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popEnterTransition = {
                    //fadeIn(animationSpec = tween(durationMillis = 300))
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popExitTransition = {
                    //fadeOut(animationSpec = tween(durationMillis = 300))
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                }
            ) {
                AllCategoriesScreen(navController, homeScreenViewModel)
            }

            composable(
                route = NavRoutes.WallpaperByCategory.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))

                }
            ) {
                WallpaperByCategory(navController, homeScreenViewModel)
            }

            composable(
                route = NavRoutes.AccountScreen.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))

                }
            ) {
                AccountScreen(navController, profileViewModel)
            }

            composable(
                route = NavRoutes.ProfileUi.route,
                enterTransition = {
                    //fadeIn(animationSpec = tween(durationMillis = 300))
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = {
                    //fadeOut(animationSpec = tween(durationMillis = 300))
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popEnterTransition = {
                    //fadeIn(animationSpec = tween(durationMillis = 300))
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popExitTransition = {
                    //fadeOut(animationSpec = tween(durationMillis = 300))
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                }
            ) {
                ProfileUi(navController, profileViewModel)
            }

            composable(
                route = NavRoutes.FavoritesUi.route,
                enterTransition = {
                    //fadeIn(animationSpec = tween(durationMillis = 300))
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                exitTransition = {
                    //fadeOut(animationSpec = tween(durationMillis = 300))
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popEnterTransition = {
                    //fadeIn(animationSpec = tween(durationMillis = 300))
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                },
                popExitTransition = {
                    //fadeOut(animationSpec = tween(durationMillis = 300))
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                        animationSpec = tween(500, easing = FastOutSlowInEasing)
                    )
                }
            ) {
                FavoritesUi(navController)
            }

            composable(
                route = NavRoutes.SignInScreen.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                }
            ) {
                SignInScreen(navController)
            }

            composable(
                route = NavRoutes.FullPhotoByFavScreen.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                },
                popEnterTransition = {
                    fadeIn(animationSpec = tween(durationMillis = 600))
                },
                popExitTransition = {
                    fadeOut(animationSpec = tween(durationMillis = 600))
                }
            ) {
                FullPhotoByFavScreen(homeScreenViewModel, navController)
            }
        }
    )
}