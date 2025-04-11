package app.chris.aurawalls.room.favorites

import androidx.room.TypeConverter
import app.chris.aurawalls.model.curated_photos.PhotoSrc
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PhotoSrcConverter {
    @TypeConverter
    fun fromPhotoSrc(photoSrc: PhotoSrc): String {
        return Gson().toJson(photoSrc)
    }

    @TypeConverter
    fun toPhotoSrc(photoSrcString: String): PhotoSrc {
        val type = object : TypeToken<PhotoSrc>() {}.type
        return Gson().fromJson(photoSrcString, type)
    }
}