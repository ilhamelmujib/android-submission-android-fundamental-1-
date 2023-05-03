package id.ilhamelmujib.submissionandroidfundamental.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import id.ilhamelmujib.submissionandroidfundamental.data.local.entity.UserEntity
import id.ilhamelmujib.submissionandroidfundamental.data.local.room.UserDao
import id.ilhamelmujib.submissionandroidfundamental.data.model.DetailUserResponse
import id.ilhamelmujib.submissionandroidfundamental.data.remote.retrofit.ApiService

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    fun searchUser(username: String): LiveData<Result<List<UserEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.searchUser(username)
            val users = response.items.map { user ->
                val isFavorite = userDao.isUserExist(user.id)
                UserEntity(
                    user.id,
                    user.login,
                    user.avatarURL,
                    user.score,
                    isFavorite
                )
            }
            emitSource(MutableLiveData(Result.Success(users)))
        } catch (e: Exception) {
            Log.d("UserRepository", "searchUser: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFavorites(): LiveData<List<UserEntity>> {
        return userDao.getUsers()
    }

    suspend fun setFavorite(user: UserEntity) {
        if (userDao.isUserExist(user.id)) {
            userDao.deleteUser(user.id)
        } else {
            userDao.insertUser(user)
        }
    }

    suspend fun deleteFavorite(id: Long) {
        userDao.deleteUser(id)
    }

    fun getDetailUser(username: String): LiveData<Result<DetailUserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.detailUser(username)
            emitSource(MutableLiveData(Result.Success(response)))
        } catch (e: Exception) {
            Log.d("UserRepository", "searchUser: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFollow(username: String, category: String): LiveData<Result<List<UserEntity>>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.followUser(username, category)
                val users = response.map { user ->
                    val isFavorite = userDao.isUserExist(user.id)
                    UserEntity(
                        user.id,
                        user.login,
                        user.avatarURL,
                        user.score,
                        isFavorite
                    )
                }
                emitSource(MutableLiveData(Result.Success(users)))
            } catch (e: Exception) {
                Log.d("UserRepository", "searchUser: ${e.message.toString()} ")
                emit(Result.Error(e.message.toString()))
            }
        }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userDao: UserDao
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userDao)
            }.also { instance = it }
    }
}