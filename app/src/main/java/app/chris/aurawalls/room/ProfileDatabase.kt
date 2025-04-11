package app.chris.aurawalls.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.chris.aurawalls.room.type_converters.Converters

@Database(entities = [Profile::class, EmailTable::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProfileDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
}