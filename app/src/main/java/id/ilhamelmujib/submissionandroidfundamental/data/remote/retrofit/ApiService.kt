package id.ilhamelmujib.submissionandroidfundamental.data.remote.retrofit

import id.ilhamelmujib.submissionandroidfundamental.data.model.DetailUserResponse
import id.ilhamelmujib.submissionandroidfundamental.data.model.UserItem
import id.ilhamelmujib.submissionandroidfundamental.data.model.UserResponse
import retrofit2.http.*

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