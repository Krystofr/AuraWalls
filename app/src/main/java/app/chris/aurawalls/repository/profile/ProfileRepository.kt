package app.chris.aurawalls.repository.profile

import app.chris.aurawalls.room.EmailTable
import app.chris.aurawalls.room.Profile
import app.chris.aurawalls.room.ProfileDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDao: ProfileDao) {

    fun getProfile(id: Int): Flow<Profile?> =
        flow {
            emit(profileDao.getProfile(id))
        }

    fun getEmail(id: Int): Flow<EmailTable?> =
        flow {
            emit(profileDao.getEmail(id))
        }

    fun getAllProfiles(): Flow<List<Profile>> =
        profileDao.getAllProfiles()

    suspend fun saveProfile(profile: Profile) {
        profileDao.insertProfile(profile)
    }

    suspend fun insertEmail(email: EmailTable) {
        profileDao.insertEmail(email)
    }

    suspend fun deleteProfile(profile: Profile) {
        profileDao.deleteProfile(profile)
    }
}