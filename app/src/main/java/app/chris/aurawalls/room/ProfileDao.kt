package app.chris.aurawalls.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Query("SELECT * FROM account WHERE id = :id")
    suspend fun getProfile(id: Int): Profile?

    @Query("SELECT * FROM account")
    fun getAllProfiles(): Flow<List<Profile>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: Profile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmail(email: EmailTable)

    @Query("SELECT * FROM email_table WHERE id = :id")
    suspend fun getEmail(id: Int): EmailTable

    @Delete
    suspend fun deleteProfile(profile: Profile)
}