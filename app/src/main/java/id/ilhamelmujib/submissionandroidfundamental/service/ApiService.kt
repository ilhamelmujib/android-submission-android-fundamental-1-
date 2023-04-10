package id.ilhamelmujib.submissionandroidfundamental.service

import id.ilhamelmujib.submissionandroidfundamental.model.Detail
import id.ilhamelmujib.submissionandroidfundamental.model.User
import retrofit2.Call
import retrofit2.http.*
import id.ilhamelmujib.submissionandroidfundamental.model.Result

interface ApiService {
    @GET("search/users")
    fun searchUser(
        @Query("q") username: String
    ): Call<Result>

    @GET("users/{username}")
    fun detailUser(
        @Path("username") username: String
    ): Call<Detail>

    @GET("users/{username}/followers")
    fun followersUser(
        @Path("username") username: String
    ): Call<Result>

    @GET("users/{username}/following")
    fun followingUser(
        @Path("username") username: String
    ): Call<Result>

}