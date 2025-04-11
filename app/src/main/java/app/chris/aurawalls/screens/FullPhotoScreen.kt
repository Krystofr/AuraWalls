package app.chris.aurawalls.screens

import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.chris.aurawalls.R
import app.chris.aurawalls.screens.home.HomeScreenViewModel
import app.chris.aurawalls.ui.theme.Background
import app.chris.aurawalls.ui.theme.IconBackground
import app.chris.aurawalls.ui.theme.MontserratAlternatesBold
import app.chris.aurawalls.ui.theme.MontserratAlternatesMedium
import app.chris.aurawalls.ui.theme.MontserratMedium
import app.chris.aurawalls.ui.theme.Pink
import app.chris.aurawalls.ui.theme.White
import app.chris.aurawalls.util.DarkItemsStatusLightNavBars
import app.chris.aurawalls.util.LightItemsStatusLightNavBars
import app.chris.aurawalls.util.NavBar
import app.chris.aurawalls.util.setWallpaper
import app.chris.aurawalls.widgets.SetWallpaperPopup
import coil.compose.rememberAsyncImagePainter

@Composable
fun FullPhotoScreen(viewModel: HomeScreenViewModel, navController: NavController) {
    val context = LocalContext.current
    val isBackgroundImageVisible = remember {
        mutableStateOf(false)
    }

    LightItemsStatusLightNavBars(context = context)
    //DarkItemsStatusLightNavBars(context = context)

    Surface(
        modifier = Modifier
            .fillMaxSize(), color = Background
    ) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            isBackgroundImageVisible.value = false
            ContentBasedOnApiLevel(
                viewModel = viewModel,
                isBackgroundImageVisible = isBackgroundImageVisible,
                navController = navController
            )
        } else {
            isBackgroundImageVisible.value = true
            ContentBasedOnApiLevel(
                viewModel = viewModel,
                isBackgroundImageVisible = isBackgroundImageVisible,
                navController = navController
            )
        }


    }

}

@Composable
fun ContentBasedOnApiLevel(
    viewModel: HomeScreenViewModel,
    isBackgroundImageVisible: MutableState<Boolean>,
    navController: NavController
) {
    val context = LocalContext.current
    val selectedPhotoState by viewModel.selectedPhoto.collectAsStateWithLifecycle()
    val showSetWallpaperPopup = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (selectedPhotoState != null) {
            if (isBackgroundImageVisible.value) {
                Image(
                    painter = rememberAsyncImagePainter(model = selectedPhotoState?.src?.portrait),
                    contentDescription = selectedPhotoState?.alt
                        ?: "Photo by ${selectedPhotoState?.photographer}",
                    modifier = Modifier
                        .fillMaxSize()
                        .blur(10.dp),
                    contentScale = ContentScale.FillBounds
                )
            } else {
                Box { isBackgroundImageVisible.value = false }
            }
        }

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Photo by ${selectedPhotoState?.photographer}",
                color = White,
                fontSize = 18.sp,
                fontFamily = MontserratAlternatesBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "more about this photography",
                color = White,
                fontSize = 9.sp,
                fontFamily = MontserratAlternatesMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        selectedPhotoState?.let { photo ->
                            launchPhotographerProfile(photo.photographer_url, context)
                        }
                    },
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxHeight(0.8f)
                    .fillMaxWidth(0.8f)
                    .shadow(elevation = 40.dp, shape = RoundedCornerShape(20.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    selectedPhotoState?.let {
                        Image(
                            painter = rememberAsyncImagePainter(model = it.src.portrait),
                            contentDescription = selectedPhotoState?.alt
                                ?: "Photo by ${selectedPhotoState?.photographer}",
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 20.dp), contentAlignment = Alignment.BottomCenter
                    ) {
                        selectedPhotoState?.let { photo ->
                            IconButton(
                                onClick = {
                                    sharePhoto(
                                        context,
                                        photoUrl = photo.src.portrait,
                                        photographer = photo.photographer,
                                        alt = photo.alt
                                    )
                                },
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(color = IconBackground, shape = CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Share,
                                    contentDescription = "Share icon",
                                    tint = White,
                                    modifier = Modifier.size(25.dp)
                                )
                            }

                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Button(
                            onClick = {
                                showSetWallpaperPopup.value = true
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Pink),
                            shape = CircleShape,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(40.dp),
                            contentPadding = PaddingValues(all = 8.dp)
                        ) {
                            Text(
                                text = "Set as Wallpaper",
                                fontFamily = MontserratMedium,
                                color = White,
                                textAlign = TextAlign.Center,
                                fontSize = 13.sp
                            )
                        }

                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.TopStart
        ) {
            NavBar(
                icon = Icons.Filled.ArrowBack,
                startPadding = 5.dp,
                topPadding = 30.dp,
                onClick = {
                    navController.popBackStack()
                }
            )
        }

    }

    if (showSetWallpaperPopup.value) {
        selectedPhotoState?.let { photo ->
            SetWallpaperPopup(
                onDismissRequest = { showSetWallpaperPopup.value = false },
                onHomeScreenClick = {
                    setWallpaper(
                        context,
                        photo.src.portrait,
                        WallpaperManager.FLAG_SYSTEM
                    )
                    Toast.makeText(context, "Photo set on Home screen", Toast.LENGTH_SHORT).show()
                    showSetWallpaperPopup.value = false
                },
                onLockScreenClick = {
                    setWallpaper(
                        context,
                        photo.src.portrait,
                        WallpaperManager.FLAG_LOCK
                    )
                    Toast.makeText(context, "Photo set on Lock screen", Toast.LENGTH_SHORT).show()
                    showSetWallpaperPopup.value = false
                },
                onBothScreenClick = {
                    setWallpaper(
                        context,
                        photo.src.portrait,
                        WallpaperManager.FLAG_LOCK
                    )
                    setWallpaper(
                        context,
                        photo.src.portrait,
                        WallpaperManager.FLAG_SYSTEM
                    )
                    Toast.makeText(
                        context,
                        context.getString(R.string.wallpaper_set), Toast.LENGTH_LONG
                    )
                        .show()
                    showSetWallpaperPopup.value = false
                },
                title = "Set wallpaper on",
                subTitle = "",
                icon = Icons.Default.CheckCircle
            )

        }

    }
}

fun sharePhoto(context: Context, photoUrl: String, photographer: String, alt: String?) {
    val shareText =
        "Check out this photo by $photographer: ${alt ?: "No description available."} \n$photoUrl"
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, "Share Photo to..."))
}

fun launchPhotographerProfile(url: String, context: Context) {
    val color = ContextCompat.getColor(context, R.color.toolbar_color)
    val defaultColors = CustomTabColorSchemeParams.Builder().setToolbarColor(color).build()

    val customTabsIntent = CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(defaultColors)
        .setShowTitle(true)
        .setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        .build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}