package app.chris.aurawalls.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "email_table")
data class EmailTable(
    @PrimaryKey val id: Int = 0,
    val email: String
)
