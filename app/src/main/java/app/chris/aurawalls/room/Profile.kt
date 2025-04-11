package app.chris.aurawalls.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class Profile(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val imageUri: String?,
)
