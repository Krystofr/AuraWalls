package app.chris.aurawalls.util

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowInsetsControllerCompat
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.BackgroundDarker
import app.chris.aurawalls.ui.theme.Transparent

@Composable
fun DarkItemsStatusLightNavBars(context: Context) {
    LaunchedEffect(Unit) {
        val activity = context as Activity
        val windowInsetsController =
            WindowInsetsControllerCompat(activity.window, activity.window.decorView)
        windowInsetsController.isAppearanceLightNavigationBars = true
        windowInsetsController.isAppearanceLightStatusBars = true

        activity.window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        activity.window.statusBarColor = Transparent.toArgb()
        activity.window.navigationBarColor = Transparent.toArgb()

       /* onDispose {
            windowInsetsController.isAppearanceLightNavigationBars = true
            windowInsetsController.isAppearanceLightStatusBars = true
            // Revert the flags when the composable is removed from the tree
            activity.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            activity.window?.statusBarColor = Transparent.toArgb()
            activity.window?.navigationBarColor = Transparent.toArgb()
        }*/
    }
}

@Composable
fun LightItemsStatusLightNavBars(context: Context) {

    LaunchedEffect(Unit) {
        val activity = context as Activity
        val windowInsetsController =
            WindowInsetsControllerCompat(activity.window, activity.window.decorView)
        windowInsetsController.isAppearanceLightNavigationBars = false
        windowInsetsController.isAppearanceLightStatusBars = false

        activity.window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        activity.window.statusBarColor = Transparent.toArgb()
        activity.window.navigationBarColor = Background.toArgb()
    }
}