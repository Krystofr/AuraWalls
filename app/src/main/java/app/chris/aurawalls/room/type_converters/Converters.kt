package app.chris.aurawalls.room.type_converters

import android.util.Base64
import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromByteArray(byteArray: ByteArray?): String? {
        return byteArray?.let { Base64.encodeToString(it, Base64.DEFAULT) }
    }

    @TypeConverter
    fun toByteArray(encodedString: String?): ByteArray? {
        return encodedString?.let { Base64.decode(it, Base64.DEFAULT) }
    }
}