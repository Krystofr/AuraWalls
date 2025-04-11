package app.chris.aurawalls.util

import android.app.WallpaperManager
import android.content.Context
import androidx.core.graphics.drawable.toBitmap
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*fun setWallpaper(context: Context, imageUrl: String, wallpaperType: Int) {
    val coroutineScope = CoroutineScope()
    val loader = context.imageLoader
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .allowHardware(false)
        .target { drawable ->
            val bitmap = (drawable as BitmapDrawable).bitmap
            WallpaperManager.getInstance(context).setBitmap(bitmap, null, true, wallpaperType)
        }
        .build()
    val result = loader.execute(request)
    if (result is SuccessResult) {
        // Handle success
    }
}*/

fun setWallpaper(context: Context, imageUrl: String, wallpaperType: Int) {
    CoroutineScope(Dispatchers.IO).launch {
        val loader = context.imageLoader
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false) // Important for manipulation of image bitmap
            .build()

        val result = loader.execute(request)
        if (result is SuccessResult) {
            // Get the bitmap from the result
            val bitmap = result.drawable.toBitmap()

            // Set the wallpaper on a background thread
            withContext(Dispatchers.Main) {
                try {
                    WallpaperManager.getInstance(context)
                        .setBitmap(bitmap, null, true, wallpaperType)
                } catch (e: Exception) {
                    // Handle any errors, e.g., by logging or showing a UI message
                }
            }
        }
    }
}