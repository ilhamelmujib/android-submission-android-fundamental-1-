package id.ilhamelmujib.submissionandroidfundamental.data.remote.retrofit

import androidx.lifecycle.LiveData
import id.ilhamelmujib.submissionandroidfundamental.data.model.DetailUserResponse
import retrofit2.Call
import retrofit2.http.*
import id.ilhamelmujib.submissionandroidfundamental.data.model.UserResponse
import id.ilhamelmujib.submissionandroidfundamental.data.model.UserItem

interface ApiService {
    @GET("search/users")
    suspend fun searchUser(
        @Query("q") username: String
    ): UserResponse

    @GET("users/{username}")
    suspend fun detailUser(
        @Path("username") username: String
    ): DetailUserResponse

    @GET("users/{username}/{category}")
    suspend fun followUser(
        @Path("username") username: String,
        @Path("category") category: String
    ): List<UserItem>


}