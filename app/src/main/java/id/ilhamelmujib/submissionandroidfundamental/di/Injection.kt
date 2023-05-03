package id.ilhamelmujib.submissionandroidfundamental.di

import android.content.Context
import id.ilhamelmujib.submissionandroidfundamental.data.UserRepository
import id.ilhamelmujib.submissionandroidfundamental.data.local.room.UserDatabase
import id.ilhamelmujib.submissionandroidfundamental.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        return UserRepository.getInstance(apiService, dao)
    }
}